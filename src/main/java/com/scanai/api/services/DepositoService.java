package com.scanai.api.services;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.*;
import com.scanai.api.domain.depositomostro.DepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosTrasfegaDepositoMostro;
import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosTrasfegaDepositoPeDeCuba;
import com.scanai.api.domain.depositovinho.Depositovinho;
import com.scanai.api.domain.depositovinho.dto.DadosTrasfegaDepositoVinho;
import com.scanai.api.repositories.DepositoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository repository;

    @Autowired
    private DepositoMostroService depositoMostroService;

    @Autowired
    private DepositoVinhoService depositoVinhoService;

    @Autowired
    private DepositoPedecubaService depositoPedecubaService;

    public Deposito register(DadosCadastroDeposito data){
        Deposito newDeposito = new Deposito(data);
        repository.save(newDeposito);
        return newDeposito;
    }

    public Deposito update(DadosAtualizarDeposito data){
        Deposito deposito = repository.findByNumerodeposito(data.numeroAtual());
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
        return repository.findAllByValidTrue();
    }

    public Deposito getElement(Long id){
        return repository.findDepositoById(id);
    }

    public List<DadosInformacoesDepositos> getAllDepositosWithInformations(){
        return repository.getAllDepositosWithInformations();
    }

    public DadosInformacoesDepositos getDepositoWithIdWithInformations(Long id) {
        return repository.getDepositoWithIdWithInformations(id);
    }

    public DadosDetalhamentoTrasfegaDeposito realizarTrasfega(DadosTrasfegaDeposito data) throws BadRequestException {
        if(Objects.equals(data.tipo(), "Mostro")){
            DepositoMostro trasfega = depositoMostroService.trasfegaMostro(new DadosTrasfegaDepositoMostro(data.idLiquidoOrigem(), data.idDepositoDestino(), LocalDate.now(), data.fkfuncionario(), data.volumetrasfega(), data.volumechegada()));
            return new DadosDetalhamentoTrasfegaDeposito("Mostro", trasfega.getFkmostro(), data.idDepositoDestino(), data.fkfuncionario(), "Trasfega de Mostro realizada com sucesso");
        }else if(Objects.equals(data.tipo(), "Vinho")){
            Depositovinho trasfega = depositoVinhoService.trasfegaVinho(new DadosTrasfegaDepositoVinho(data.idLiquidoOrigem(), data.idDepositoDestino(), LocalDate.now(), data.fkfuncionario(), data.volumetrasfega(), data.volumechegada()));
            return new DadosDetalhamentoTrasfegaDeposito("Vinho", trasfega.getFkvinho(), data.idDepositoDestino(), data.fkfuncionario(), "Trasfega de Vinho realizada com sucesso");
        } else if (Objects.equals(data.tipo(), "PeDeCuba")) {
            Depositopedecuba trasfega = depositoPedecubaService.trasfegaPedecuba(new DadosTrasfegaDepositoPeDeCuba(data.idLiquidoOrigem(), data.idDepositoDestino(), LocalDate.now(), data.fkfuncionario(), data.volumetrasfega(), data.volumechegada()));
            return new DadosDetalhamentoTrasfegaDeposito("PeDeCuba", trasfega.getFkpedecuba(), data.idDepositoDestino(), data.fkfuncionario(), "Trasfega de PeDeCuba realizada com sucesso");
        }else{
            throw new BadRequestException("Tipo de trasfega invalida");
        }
    }
}
