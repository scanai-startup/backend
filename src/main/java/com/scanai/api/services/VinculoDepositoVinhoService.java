package com.scanai.api.services;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.DadosInformacoesDepositos;
import com.scanai.api.domain.depositovinho.dto.DadosCadastroDepositoVinho;
import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.rotulo.Rotulo;
import com.scanai.api.domain.vinculodepositovinho.dto.DadosCadastroVinculoDepositoVinho;
import com.scanai.api.domain.vinculodepositovinho.dto.DadosDetalhamentoVinculoDepositoVinho;
import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import com.scanai.api.domain.vinho.Vinho;
import com.scanai.api.repositories.DepositoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

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

    @Autowired
    private DepositoRepository _depositoRepository;

    @Transactional
    public DadosDetalhamentoVinculoDepositoVinho vincularDepositoVinho(DadosCadastroVinculoDepositoVinho data) {
        // Verificar se o depósito existe e é válido
        Deposito deposito = _depositoService.getElement(data.depositoId());

        DadosInformacoesDepositos dadosInformacoesDepositos = _depositoRepository.getDepositoWithIdWithInformations(deposito.getId());

        if (deposito == null) {
            throw new IllegalArgumentException("Depósito não encontrado");
        }
        if(dadosInformacoesDepositos.getConteudo().equals("Vinho")){
            throw new IllegalArgumentException("Depósito já contém um vinho ativo");
        }
        if(data.volumeChegadaPedecuba() > data.volumeTrasfegaPedecuba()){
            throw new IllegalArgumentException("Volume de chegada do pé de cuba maior que o volume de trasfega");
        }
        if(data.volumeChegadaMostro() > data.volumeTrasfegaMostro()){
            throw new IllegalArgumentException("Volume de chegada do mostro maior que o volume de trasfega");
        }


        // Verificar se o Pé de Cuba existe e é válido
        Pedecuba pedecuba = _pedecubaService.getElement(data.pedecubaId());
        if (pedecuba == null) {
            throw new IllegalArgumentException("Pé de Cuba não encontrado");
        }
        if(data.volumeTrasfegaPedecuba() > pedecuba.getVolume()){
            throw new IllegalArgumentException("Volume de trasfega do pé de cuba maior que o volume do pé de cuba");
        }
        // se no deposito tiver o pe de cuba que foi enviado pra vinculação, então deve ver se o volume colocado
        // pra vinculação é referente a totalidade do pe de cuba
        if(Objects.equals(dadosInformacoesDepositos.getIdConteudo(), pedecuba.getId())){
            if(pedecuba.getVolume() != data.volumeTrasfegaPedecuba()){
                throw new IllegalArgumentException("Argumento inválido: Não é possivel criar um vinho num deposito que contém pe de cuba sem usar o volume total do pe de cuba");
            }
        }

        // Verificar se o rótulo existe e é válido
        Rotulo rotulo = _rotuloService.getElement(data.rotuloId());
        if(rotulo == null){
            throw new IllegalArgumentException("Rótulo não encontrado");
        }
        // Verificar se o mostro existe e é valido
        Mostro mostro = _mostroService.getElement(data.mostroId());
        if(mostro == null){
            throw new IllegalArgumentException("Mostro não encontrado");
        }
        if(data.volumeTrasfegaMostro() > mostro.getVolume()){
            throw new IllegalArgumentException("Volume de trasfega do mostro maior que o volume do mostro");
        }
        if(Objects.equals(dadosInformacoesDepositos.getIdConteudo(), mostro.getId())){
            System.out.println(mostro.getVolume() +" "+data.volumeTrasfegaMostro());
            if(!Objects.equals(mostro.getVolume(), data.volumeTrasfegaMostro())){
                throw new IllegalArgumentException("Argumento inválido: Não é possivel criar um vinho num deposito que contém mostro sem usar o volume total do mostro");
            }
        }

        // Somando o volume do pe de cuba ao volume do vinho
        Float volumeVinho = data.volumeChegadaPedecuba();

        // Aplicando softdelete no pé de cuba
        _depositoPedecubaService.setDataFim(data.pedecubaId());
        pedecuba.setValid(false);
        pedecuba.setDatafimfermentacao(LocalDate.now());



        Vinho vinho = null;
        volumeVinho += data.volumeChegadaMostro();

        // Diminuir o volume do mostro e adicionar ao volume do vinho
        if(mostro.getVolume() < data.volumeTrasfegaMostro() ){
            throw new IllegalArgumentException("Inválido: Volume de mostro insuficiente");
        }

        if(mostro.getVolume().equals(data.volumeTrasfegaMostro())){

            // Criando registro de vinhoo e Vinculando ao mostro e ao pe de cuba
            vinho = _vinhoService.register(new DadosCadastroVinho(data.mostroId(), volumeVinho, data.rotuloId(), pedecuba.getId()));

            // aplicando soft delete no mostro
            mostro.setValid(false);
            _depositoMostroService.setDataFim(data.mostroId());
            mostro.setFimfermentacao(LocalDate.now());

        }else if(mostro.getVolume() > data.volumeTrasfegaMostro()){
            // Diminuindo o volume do mostro
            mostro.setVolume(mostro.getVolume()-data.volumeTrasfegaMostro());

            // Criando um novo mostro com o volume restante
            Mostro novoMostro = _mostroService.register(new DadosCadastroMostro(data.funcionarioId(), data.volumeChegadaMostro(), mostro.getId(), null));

            // Criando registro de vinho e Vinculando ao mostro
            //System.out.println(data.rotuloId());
            vinho = _vinhoService.register(new DadosCadastroVinho(novoMostro.getId(), volumeVinho, data.rotuloId(), pedecuba.getId()));
            System.out.println(vinho.getFkmostro());
        }

        // Relacionando vinho com deposito
        _depositoVinhoService.register(new DadosCadastroDepositoVinho(vinho.getId(), deposito.getId(), LocalDate.now(), data.funcionarioId()));

        String message = "Vinho " + rotulo.getNome()+" : "+rotulo.getTipo()+" criado e vinculado com sucesso.";

        if (dadosInformacoesDepositos.getConteudo() != null)
            message += "Vinculado ao deposito que continha " + (Objects.equals(dadosInformacoesDepositos.getConteudo(), "Pé De Cuba") ? "Pé de cuba" : "Mostro");

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
