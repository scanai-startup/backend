package com.scanai.api.services.implement;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.*;
import com.scanai.api.domain.depositomostro.DepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosDetalhamentoDepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosTrasfegaDepositoMostro;
import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosDetalhamentoDepositoPeDeCuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosTrasfegaDepositoPeDeCuba;
import com.scanai.api.domain.depositovinho.Depositovinho;
import com.scanai.api.domain.depositovinho.dto.DadosTrasfegaDepositoVinho;
import com.scanai.api.infra.exceptions.customExceptions.BadRequest;
import com.scanai.api.repositories.DepositoRepository;
import com.scanai.api.services.DepositoMostroServiceInterface;
import com.scanai.api.services.DepositoPeDeCubaServiceInterface;
import com.scanai.api.services.DepositoServiceInterface;
import com.scanai.api.services.DepositoVinhoServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepositoService implements DepositoServiceInterface {

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    private DepositoMostroServiceInterface depositoMostroService;

    @Autowired
    private DepositoVinhoServiceInterface depositoVinhoService;

    @Autowired
    private DepositoPeDeCubaServiceInterface depositoPeDeCubaService;

    public DadosDetalhamentoDeposito register(DadosCadastroDeposito data){
        Deposito newDeposito = new Deposito(data);
        depositoRepository.save(newDeposito);
        return new DadosDetalhamentoDeposito(newDeposito);
    }

    public DadosDetalhamentoDeposito update(DadosAtualizarDeposito data){
        Optional<Deposito> depositoOpt = depositoRepository.findByNumerodeposito(data.numeroAtual());

        if(depositoOpt.isEmpty()){
            throw new BadRequest("Deposito not found");
        }

        Deposito deposito = depositoOpt.get();
        deposito.setNumerodeposito(data.numeroNovo());

        return new DadosDetalhamentoDeposito(deposito);
    }

    public void softDelete(Deposito deposito) {
        deposito.setValid(false);
    }

    public void activate(Deposito deposito) {
        deposito.setValid(true);
    }

    public List<Deposito> getAll(){
        return depositoRepository.findAllByValidTrue();
    }

    public DadosDetalhamentoDeposito getElement(Long id){
        Optional<Deposito> depositoOpt = depositoRepository.findById(id);

        if(depositoOpt.isEmpty()){
            throw new EntityNotFoundException("Deposito not found");
        }

        Deposito deposito = depositoOpt.get();

        return new DadosDetalhamentoDeposito(deposito);
    }

    public List<DadosInformacoesDepositos> getAllDepositosWithInformations(){
        return depositoRepository.getAllDepositosWithInformations();
    }

    public DadosInformacoesDepositos getDepositoWithIdWithInformations(Long id) {
        Optional<DadosInformacoesDepositos> infoDeposito = depositoRepository.getDepositoWithIdWithInformations(id);

        if(infoDeposito.isEmpty()){
            throw new BadRequest("Deposito not found");
        }

        return infoDeposito.get();
    }

    public DadosDetalhamentoTrasfegaDeposito realizarTrasfega(DadosTrasfegaDeposito data) {
        switch (data.tipo()) {
            case "Mostro" -> {
                DadosDetalhamentoDepositoMostro trasfega = depositoMostroService.trasfegaMostro(new DadosTrasfegaDepositoMostro(data.idLiquidoOrigem(), data.idDepositoDestino(), LocalDate.now(), data.fkfuncionario(), data.volumetrasfega(), data.volumechegada()));
                return new DadosDetalhamentoTrasfegaDeposito("Mostro", trasfega.fkmostro(), data.idDepositoDestino(), data.fkfuncionario(), "Trasfega de Mostro realizada com sucesso");
            }
            case "Vinho" -> {
                Depositovinho trasfega = depositoVinhoService.trasfegaVinho(new DadosTrasfegaDepositoVinho(data.idLiquidoOrigem(), data.idDepositoDestino(), LocalDate.now(), data.fkfuncionario(), data.volumetrasfega(), data.volumechegada()));
                return new DadosDetalhamentoTrasfegaDeposito("Vinho", trasfega.getFkvinho(), data.idDepositoDestino(), data.fkfuncionario(), "Trasfega de Vinho realizada com sucesso");
            }
            case "PeDeCuba" -> {
                DadosDetalhamentoDepositoPeDeCuba trasfega = depositoPeDeCubaService.trasfegaPedecuba(new DadosTrasfegaDepositoPeDeCuba(data.idLiquidoOrigem(), data.idDepositoDestino(), LocalDate.now(), data.fkfuncionario(), data.volumetrasfega(), data.volumechegada()));
                return new DadosDetalhamentoTrasfegaDeposito("PeDeCuba", trasfega.fkpedecuba(), data.idDepositoDestino(), data.fkfuncionario(), "Trasfega de PeDeCuba realizada com sucesso");
            }
            case null, default -> throw new BadRequest("Tipo de trasfega invalida");
        }
    }

    public DadosResumoDepositos resumoDepositos(){
        int emUso = 0;
        int emManutencao = 0;
        int livre = 0;

        List<Deposito> depositos = depositoRepository.findAll();

        for (Deposito deposito : depositos) {
            if (depositoEmUso(deposito.getId())) {
                emUso++;
            } else if (!deposito.isValid()) {
                emManutencao++;
            }
        }

        livre = depositos.size() - (emUso + emManutencao);

        return new DadosResumoDepositos(emUso, emManutencao, livre);
    }

    private Boolean depositoEmUso(Long idDeposito){
        return depositoRepository.existsVinhoAtivo(idDeposito).isPresent() || depositoRepository.existsPeDeCubaAtivo(idDeposito).isPresent() || depositoRepository.existsMostroAtivo(idDeposito).isPresent();
    }
}
