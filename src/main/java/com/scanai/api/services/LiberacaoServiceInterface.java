package com.scanai.api.services;

import com.scanai.api.domain.liberacao.Liberacao;
import com.scanai.api.domain.liberacao.dto.DadosAtualizarLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosCadastroLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosDetalhamentoLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosListagemLiberacao;

import java.util.List;

public interface LiberacaoServiceInterface {

    DadosDetalhamentoLiberacao register(DadosCadastroLiberacao dados);

    List<DadosListagemLiberacao> getAll();

    DadosDetalhamentoLiberacao getElement(Long id);

    void hardDelete(Long id);

    DadosDetalhamentoLiberacao update(DadosAtualizarLiberacao dados);
}
