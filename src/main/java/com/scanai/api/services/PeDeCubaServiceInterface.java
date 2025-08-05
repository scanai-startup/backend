package com.scanai.api.services;

import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;

public interface PeDeCubaServiceInterface {

    Pedecuba register(DadosCadastroPeDeCuba dados);

    void softDelete(Long id);

    void activate(Long id);

    Pedecuba getElement(Long id);
}
