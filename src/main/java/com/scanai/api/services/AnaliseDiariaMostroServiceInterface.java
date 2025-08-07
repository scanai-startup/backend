package com.scanai.api.services;

import com.scanai.api.domain.analisediariamostro.AnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosAtualizarAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosCadastroAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosDetalhamentoAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosListagemAnaliseDiariaMostro;

import java.util.List;

public interface AnaliseDiariaMostroServiceInterface {

    DadosDetalhamentoAnaliseDiariaMostro register(DadosCadastroAnaliseDiariaMostro dados);

    List<DadosListagemAnaliseDiariaMostro> getAll();

    DadosDetalhamentoAnaliseDiariaMostro getElement(Long id);

    void hardDelete(Long id);

    DadosDetalhamentoAnaliseDiariaMostro update(DadosAtualizarAnaliseDiariaMostro dados);
}
