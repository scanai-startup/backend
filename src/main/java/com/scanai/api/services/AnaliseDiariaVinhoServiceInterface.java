package com.scanai.api.services;

import com.scanai.api.domain.analisediariavinho.AnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosAtualizarAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosCadastroAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosDetalhamentoAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosListagemAnaliseDiariaVinho;

import java.util.List;

public interface AnaliseDiariaVinhoServiceInterface {

    AnaliseDiariaVinho register(DadosCadastroAnaliseDiariaVinho dados);

    List<DadosListagemAnaliseDiariaVinho> getAll();

    AnaliseDiariaVinho getElement(Long id);

    void hardDelete(Long id);

    DadosDetalhamentoAnaliseDiariaVinho update(DadosAtualizarAnaliseDiariaVinho dados);
}
