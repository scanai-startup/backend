package com.scanai.api.services;

import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.domain.mostro.dto.DadosDetalhamentoMostro;
import com.scanai.api.domain.mostro.dto.DadosListagemMostro;

import java.util.List;

public interface MostroServiceInterface {

    DadosDetalhamentoMostro register(DadosCadastroMostro data);

    void softDelete(Long id);

    void activate(Long id);

    DadosDetalhamentoMostro getElement(Long id);

    Mostro createMostroFilho(Long idMostroOrigem, float volumeMostroFilho, float volumeRetiradoMostroOrigem, Long idFuncionario);

    List<DadosListagemMostro> getAll();
}
