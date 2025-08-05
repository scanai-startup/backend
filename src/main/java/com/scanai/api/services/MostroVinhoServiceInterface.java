package com.scanai.api.services;

import com.scanai.api.domain.mostrovinho.MostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosAtualizarMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosCadastroMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosDetalhamentoMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosListagemMostroVinho;

import java.util.List;

public interface MostroVinhoServiceInterface {

    MostroVinho register(DadosCadastroMostroVinho dados);

    MostroVinho getElement(Long id);

    List<DadosListagemMostroVinho> getAll();

    void hardDelete(Long id);

    DadosDetalhamentoMostroVinho update(DadosAtualizarMostroVinho dados);
}
