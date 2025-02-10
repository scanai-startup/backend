package com.scanai.api.services;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.depositovinho.dto.DadosCadastroDepositoVinho;
import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.rotulo.Rotulo;
import com.scanai.api.domain.vinculodepositovinho.dto.DadosCadastroVinculoDepositoVinho;
import com.scanai.api.domain.vinculodepositovinho.dto.DadosDetalhamentoVinculoDepositoVinho;
import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import com.scanai.api.domain.vinho.Vinho;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VinculoDepositoVinhoService {
    @Autowired
    private VinhoService _vinhoService;

    @Autowired
    private PedecubaService _pedecubaService;

    @Autowired
    private MostroService _mostroService;

    @Autowired
    private RotuloService _rotuloService;

    @Autowired
    private DepositoService _depositoService;

    @Autowired
    private DepositoVinhoService _depositoVinhoService;

    @Autowired
    private DepositoPedecubaService _depositoPedecubaService;

    @Autowired
    private DepositoMostroService _depositoMostroService;


    @Transactional
    public DadosDetalhamentoVinculoDepositoVinho vincularDepositoVinho(DadosCadastroVinculoDepositoVinho data) {
        // Verificar se o depósito existe e é válido
        Deposito deposito = _depositoService.getElement(data.depositoId());
        if (deposito == null) {
            throw new IllegalArgumentException("Depósito não encontrado");
        }

        // Verificar se o Pé de Cuba existe e é válido
        Pedecuba pedecuba = _pedecubaService.getElement(data.pedecubaId());
        if (pedecuba == null) {
            throw new IllegalArgumentException("Pé de Cuba não encontrado");
        }
        if(pedecuba.getVolume() < data.pedecubaVolumePerda()){
            throw new IllegalArgumentException("Argumento inválido: Volume de perda maior que o volume do Pé de Cuba");
        }

        // Somando o volume do pe de cuba ao volume do vinho
        Float volumeVinho = pedecuba.getVolume() - data.pedecubaVolumePerda();

        // Aplicando softdelete no pé de cuba
        _depositoPedecubaService.setDataFim(data.pedecubaId());
        pedecuba.setValid(false);
        pedecuba.setDatafimfermentacao(LocalDate.now());



        // Verificar se o rótulo existe e é válido
        Rotulo rotulo = _rotuloService.getElement(data.rotuloId());
        if(rotulo == null){
            throw new IllegalArgumentException("Rótulo não encontrado");
        }

        Mostro mostro = _mostroService.getElement(data.mostroId());
        if(mostro == null){
            throw new IllegalArgumentException("Mostro não encontrado");
        }

        Vinho vinho = null;
        volumeVinho += data.volumeMostro();

        // Diminuir o volume do mostro e adicionar ao volume do vinho
        if(mostro.getVolume() < (data.volumeMostro()+data.volumeMostroPerda()) ){
            throw new IllegalArgumentException("Inválido: A soma do volume inserido do mostro com o volume da perda é maior que o volume do mostro");
        }
        else if(mostro.getVolume() == (data.volumeMostro()+data.volumeMostroPerda())){

            // Criando registro de vinhoo e Vinculando ao mostro e ao pe de cuba
             vinho = _vinhoService.register(new DadosCadastroVinho(data.mostroId(), data.dataFimFermentacao(), volumeVinho, data.rotuloId(), pedecuba.getId()));

            // aplicando soft delete no mostro
            mostro.setValid(false);
            _depositoMostroService.setDataFim(data.mostroId());
            mostro.setFimfermentacao(LocalDate.now());

        }else{
            // Diminuindo o volume do mostro
            mostro.setVolume(mostro.getVolume()-data.volumeMostro()-data.volumeMostroPerda());

            // Criando um novo mostro com o volume restante
            Mostro novoMostro = _mostroService.register(new DadosCadastroMostro(data.funcionarioId(), data.volumeMostro(), mostro.getId(), null));

            // Criando registro de vinho e Vinculando ao mostro
            vinho = _vinhoService.register(new DadosCadastroVinho(novoMostro.getId(), data.dataFimFermentacao(), volumeVinho, data.rotuloId(), pedecuba.getId()));

        }
        // Relacionando vinho com deposito
        _depositoVinhoService.register(new DadosCadastroDepositoVinho(vinho.getId(), deposito.getId(), LocalDate.now(), data.funcionarioId()));


        String message = "Vinho " + rotulo.getNome()+" : "+rotulo.getTipo()+" criado e vinculado com sucesso";
        // Retornar os detalhes do vínculo criado
        return new DadosDetalhamentoVinculoDepositoVinho(
                vinho.getId(),
                vinho.getVolume(),
                deposito.getId(),
                pedecuba.getId(),
                rotulo.getId(),
                mostro.getId(),
                message
        );
    }
}
