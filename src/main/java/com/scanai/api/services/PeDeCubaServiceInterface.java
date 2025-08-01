package com.scanai.api.services;

import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;

public interface PeDeCubaServiceInterface {

    Pedecuba register(DadosCadastroPeDeCuba dados);

    void softDelete(Pedecuba pedecuba);

    void activate(Pedecuba pedecuba);

    Pedecuba getElement(Long id);
}
