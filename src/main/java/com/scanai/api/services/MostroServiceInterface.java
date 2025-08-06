package com.scanai.api.services;

import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.domain.mostro.dto.DadosListagemMostro;

import java.util.List;

public interface MostroServiceInterface {

    Mostro register(DadosCadastroMostro data);

    void softDelete(Long id);

    void activate(Long id);

    Mostro getElement(Long id);

    Mostro createMostroFilho(Long idMostroOrigem, float volumeMostroFilho, float volumeRetiradoMostroOrigem, Long idFuncionario);

    List<DadosListagemMostro> getAll();
}
