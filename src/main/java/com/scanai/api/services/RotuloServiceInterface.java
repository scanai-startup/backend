package com.scanai.api.services;

import com.scanai.api.domain.rotulo.DTO.DadosAtualizarRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosCadastroRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosDetalhamentoRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosListagemRotulo;
import com.scanai.api.domain.rotulo.Rotulo;

import java.util.List;

public interface RotuloServiceInterface {

    DadosDetalhamentoRotulo register(DadosCadastroRotulo dados);

    List<DadosListagemRotulo> listAll();

    DadosDetalhamentoRotulo getElement(Long id);

    void hardDelete(Long id);

    DadosDetalhamentoRotulo update(DadosAtualizarRotulo dados);
}
