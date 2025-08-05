package com.scanai.api.services;

import com.scanai.api.domain.vinho.DTO.DadosAtualizarVinho;
import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import com.scanai.api.domain.vinho.DTO.DadosDetalhamentoVinho;
import com.scanai.api.domain.vinho.DTO.DadosListagemVinho;
import com.scanai.api.domain.vinho.Vinho;

import java.util.List;

public interface VinhoServiceInterface {

    Vinho register(DadosCadastroVinho dados);

    List<DadosListagemVinho> getAll();

    Vinho getElement(Long id);

    void hardDelete(Long id);

    void softDelete(Long id);

    void activate(Long id);

    DadosDetalhamentoVinho update(DadosAtualizarVinho dados);
}
