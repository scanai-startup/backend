package com.scanai.api.services;

import com.scanai.api.domain.rotulagem.Rotulagem;
import com.scanai.api.domain.rotulagem.dto.DadosCadastroRotulagem;

public interface RotulagemServiceInterface {

    Rotulagem register(DadosCadastroRotulagem dados);
}
