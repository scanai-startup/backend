package com.scanai.api.services;

import com.scanai.api.domain.uva.dto.DadosAtualizarUva;
import com.scanai.api.domain.uva.dto.DadosCadastroUva;
import com.scanai.api.domain.uva.dto.DadosDetalhamentoUva;
import com.scanai.api.domain.uva.dto.DadosListagemUva;

import java.util.List;

public interface UvaServiceInterface {

    DadosDetalhamentoUva register(DadosCadastroUva dados);

    DadosDetalhamentoUva getElement(Long id);

    List<DadosListagemUva> listAllByValidTrue();

    List<DadosListagemUva> listAll();

    void hardDelete(Long id);

    DadosDetalhamentoUva update(DadosAtualizarUva dados);

    void softDelete(Long id);

    void activate(Long id);

    void addFkMostro(Long uvaId, Long mostroId);
}
