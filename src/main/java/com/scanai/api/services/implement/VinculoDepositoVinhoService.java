package com.scanai.api.services.implement;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.DadosDetalhamentoDeposito;
import com.scanai.api.domain.deposito.dto.DadosInformacoesDepositos;
import com.scanai.api.domain.depositovinho.dto.DadosCadastroDepositoVinho;
import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.domain.mostro.dto.DadosDetalhamentoMostro;
import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosDetalhamentoPeDeCuba;
import com.scanai.api.domain.rotulo.DTO.DadosDetalhamentoRotulo;
import com.scanai.api.domain.rotulo.Rotulo;
import com.scanai.api.domain.vinculodepositovinho.dto.DadosCadastroVinculoDepositoVinho;
import com.scanai.api.domain.vinculodepositovinho.dto.DadosDetalhamentoVinculoDepositoVinho;
import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import com.scanai.api.domain.vinho.Vinho;
import com.scanai.api.repositories.DepositoRepository;
import com.scanai.api.repositories.MostroRepository;
import com.scanai.api.repositories.PeDeCubaRepository;
import com.scanai.api.services.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class VinculoDepositoVinhoService implements VinculoDepositoVinhoServiceInterface {

    @Autowired
    private VinhoServiceInterface vinhoService;

    @Autowired
    private PeDeCubaServiceInterface peDeCubaService;

    @Autowired
    private MostroServiceInterface mostroService;

    @Autowired
    private RotuloServiceInterface rotuloService;

    @Autowired
    private DepositoServiceInterface depositoService;

    @Autowired
    private DepositoVinhoServiceInterface depositoVinhoService;

    @Autowired
    private DepositoPeDeCubaServiceInterface depositopeDeCubaService;

    @Autowired
    private DepositoMostroServiceInterface depositoMostroService;

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    MostroRepository mostroRepository;

    @Autowired
    PeDeCubaRepository peDeCubaRepository;

    //TODO refatorar este metodo para seguir os principios do single responsibility e Optional
    @Transactional
    public DadosDetalhamentoVinculoDepositoVinho vincularDepositoVinho(DadosCadastroVinculoDepositoVinho data) {
        // Verificar se o depósito existe e é válido
        DadosDetalhamentoDeposito deposito = depositoService.getElement(data.depositoId());
        Optional<DadosInformacoesDepositos> dadosInformacoesDepositos = depositoRepository.getDepositoWithIdWithInformations(deposito.id());

        if(dadosInformacoesDepositos.get().getConteudo() != null && dadosInformacoesDepositos.get().getConteudo().equals("Vinho")){
            throw new IllegalArgumentException("Depósito já contém um vinho ativo");
        }
        if(data.volumeChegadaPedecuba() > data.volumeTrasfegaPedecuba()){
            throw new IllegalArgumentException("Volume de chegada do pé de cuba maior que o volume de trasfega");
        }
        if(data.volumeChegadaMostro() > data.volumeTrasfegaMostro()){
            throw new IllegalArgumentException("Volume de chegada do mostro maior que o volume de trasfega");
        }

        // Verificar se o Pé de Cuba existe e é válido
        //TODO ajeitar para chamar pelo service!!!
        Pedecuba pedecuba = peDeCubaRepository.getReferenceById(data.pedecubaId());
        if (pedecuba == null) {
            throw new IllegalArgumentException("Pé de Cuba não encontrado");
        }
        if(data.volumeTrasfegaPedecuba() > pedecuba.getVolume()){
            throw new IllegalArgumentException("Volume de trasfega do pé de cuba maior que o volume do pé de cuba");
        }
        // se no deposito tiver o pe de cuba que foi enviado pra vinculação, então deve ver se o volume colocado
        // pra vinculação é referente a totalidade do pe de cuba
        if(Objects.equals(dadosInformacoesDepositos.get().getIdConteudo(), pedecuba.getId())){
            if(pedecuba.getVolume() != data.volumeTrasfegaPedecuba()){
                throw new IllegalArgumentException("Argumento inválido: Não é possivel criar um vinho num deposito que contém pe de cuba sem usar o volume total do pe de cuba");
            }
        }

        // Verificar se o rótulo existe e é válido
        //TODO ajeitar para chamar pelo service!!!
        DadosDetalhamentoRotulo rotulo = rotuloService.getElement(data.rotuloId());
        if(rotulo == null){
            throw new IllegalArgumentException("Rótulo não encontrado");
        }
        // Verificar se o mostro existe e é valido
        //TODO ajeitar para chamar pelo service!!!
        Mostro mostro = mostroRepository.getReferenceById(data.mostroId());
        if(mostro == null){
            throw new IllegalArgumentException("Mostro não encontrado");
        }
        if(data.volumeTrasfegaMostro() > mostro.getVolume()){
            throw new IllegalArgumentException("Volume de trasfega do mostro maior que o volume do mostro");
        }
        if(Objects.equals(dadosInformacoesDepositos.get().getIdConteudo(), mostro.getId())){
            System.out.println(mostro.getVolume() +" "+data.volumeTrasfegaMostro());
            if(!Objects.equals(mostro.getVolume(), data.volumeTrasfegaMostro())){
                throw new IllegalArgumentException("Argumento inválido: Não é possivel criar um vinho num deposito que contém mostro sem usar o volume total do mostro");
            }
        }

        // Somando o volume do pe de cuba ao volume do vinho
        Float volumeVinho = data.volumeChegadaPedecuba();

        // Aplicando softdelete no pé de cuba
        depositopeDeCubaService.setDataFim(data.pedecubaId());
        //TODO ajeitar para chamar pelo service!!!
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
            vinho = vinhoService.register(new DadosCadastroVinho(data.mostroId(), volumeVinho, data.rotuloId(), pedecuba.getId()));

            // aplicando soft delete no mostro
            mostro.setValid(false);
            depositoMostroService.setDataFim(data.mostroId());
            mostro.setFimfermentacao(LocalDate.now());

        }else if(mostro.getVolume() > data.volumeTrasfegaMostro()){
            // Diminuindo o volume do mostro
            mostro.setVolume(mostro.getVolume()-data.volumeTrasfegaMostro());

            // Criando um novo mostro com o volume restante
            DadosDetalhamentoMostro novoMostro = mostroService.register(new DadosCadastroMostro(data.funcionarioId(), data.volumeChegadaMostro(), mostro.getId(), null));

            // Criando registro de vinho e Vinculando ao mostro
            //System.out.println(data.rotuloId());
            vinho = vinhoService.register(new DadosCadastroVinho(novoMostro.id(), volumeVinho, data.rotuloId(), pedecuba.getId()));
            System.out.println(vinho.getFkmostro());
        }

        // Relacionando vinho com deposito
        depositoVinhoService.register(new DadosCadastroDepositoVinho(vinho.getId(), deposito.id(), LocalDate.now(), data.funcionarioId()));

        String message = "Vinho " + rotulo.nome()+" : "+rotulo.tipo()+" criado e vinculado com sucesso.";

        if (dadosInformacoesDepositos.get().getConteudo() != null)
            message += "Vinculado ao deposito que continha " + (Objects.equals(dadosInformacoesDepositos.get().getConteudo(), "Pé De Cuba") ? "Pé de cuba" : "Mostro");

        // Retornar os detalhes do vínculo criado
        return new DadosDetalhamentoVinculoDepositoVinho(
                vinho.getId(),
                vinho.getVolume(),
                deposito.id(),
                pedecuba.getId(),
                rotulo.id(),
                mostro.getId(),
                message
        );

    }
}
