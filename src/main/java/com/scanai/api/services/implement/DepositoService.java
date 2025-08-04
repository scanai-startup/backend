package com.scanai.api.services.implement;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.*;
import com.scanai.api.domain.depositomostro.DepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosTrasfegaDepositoMostro;
import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
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
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    public Deposito register(DadosCadastroDeposito data){
        Deposito newDeposito = new Deposito(data);
        depositoRepository.save(newDeposito);
        return newDeposito;
    }

    public Deposito update(DadosAtualizarDeposito data){
        Deposito deposito = depositoRepository.findByNumerodeposito(data.numeroAtual());

        if(deposito == null){
            throw new EntityNotFoundException("Deposito: " + data.numeroAtual() + " Não Encontrado");
        }

        deposito.setNumerodeposito(data.numeroNovo());

        return deposito;
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

    public Deposito getElement(Long id){
        return depositoRepository.findDepositoById(id);
    }

    public List<DadosInformacoesDepositos> getAllDepositosWithInformations(){
        return depositoRepository.getAllDepositosWithInformations();
    }

    public DadosInformacoesDepositos getDepositoWithIdWithInformations(Long id) {
        return depositoRepository.getDepositoWithIdWithInformations(id);
    }

    public DadosDetalhamentoTrasfegaDeposito realizarTrasfega(DadosTrasfegaDeposito data) {
        switch (data.tipo()) {
            case "Mostro" -> {
                DepositoMostro trasfega = depositoMostroService.trasfegaMostro(new DadosTrasfegaDepositoMostro(data.idLiquidoOrigem(), data.idDepositoDestino(), LocalDate.now(), data.fkfuncionario(), data.volumetrasfega(), data.volumechegada()));
                return new DadosDetalhamentoTrasfegaDeposito("Mostro", trasfega.getFkmostro(), data.idDepositoDestino(), data.fkfuncionario(), "Trasfega de Mostro realizada com sucesso");
            }
            case "Vinho" -> {
                Depositovinho trasfega = depositoVinhoService.trasfegaVinho(new DadosTrasfegaDepositoVinho(data.idLiquidoOrigem(), data.idDepositoDestino(), LocalDate.now(), data.fkfuncionario(), data.volumetrasfega(), data.volumechegada()));
                return new DadosDetalhamentoTrasfegaDeposito("Vinho", trasfega.getFkvinho(), data.idDepositoDestino(), data.fkfuncionario(), "Trasfega de Vinho realizada com sucesso");
            }
            case "PeDeCuba" -> {
                Depositopedecuba trasfega = depositoPeDeCubaService.trasfegaPedecuba(new DadosTrasfegaDepositoPeDeCuba(data.idLiquidoOrigem(), data.idDepositoDestino(), LocalDate.now(), data.fkfuncionario(), data.volumetrasfega(), data.volumechegada()));
                return new DadosDetalhamentoTrasfegaDeposito("PeDeCuba", trasfega.getFkpedecuba(), data.idDepositoDestino(), data.fkfuncionario(), "Trasfega de PeDeCuba realizada com sucesso");
            }
            case null, default -> throw new BadRequest("Tipo de trasfega invalida");
        }
    }
}
