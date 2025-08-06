package com.scanai.api.services;

import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;
import com.scanai.api.domain.pedecuba.dto.DadosDetalhamentoPeDeCuba;

import java.util.List;

public interface PeDeCubaServiceInterface {

    Pedecuba register(DadosCadastroPeDeCuba dados);

    void softDelete(Long id);

    void activate(Long id);

    Pedecuba getElement(Long id);

    List<DadosDetalhamentoPeDeCuba> getAll();
}
