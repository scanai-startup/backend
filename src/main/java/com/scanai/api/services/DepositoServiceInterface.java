package com.scanai.api.services;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.*;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface DepositoServiceInterface {

    DadosDetalhamentoDeposito register(DadosCadastroDeposito data);

    DadosDetalhamentoDeposito update(DadosAtualizarDeposito data) throws EntityNotFoundException;

    void softDelete(Deposito deposito);

    void activate(Deposito deposito);

    List<Deposito> getAll();

    DadosDetalhamentoDeposito getElement(Long id);

    List<DadosInformacoesDepositos> getAllDepositosWithInformations();

    DadosInformacoesDepositos getDepositoWithIdWithInformations(Long id);

    DadosDetalhamentoTrasfegaDeposito realizarTrasfega(DadosTrasfegaDeposito data) throws BadRequestException;

    DadosResumoDepositos resumoDepositos();
}
