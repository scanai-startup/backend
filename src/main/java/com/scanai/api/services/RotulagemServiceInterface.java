package com.scanai.api.services;

import com.scanai.api.domain.rotulagem.Rotulagem;
import com.scanai.api.domain.rotulagem.dto.DadosCadastroRotulagem;
import com.scanai.api.domain.rotulagem.dto.DadosDetalhamentoRotulagem;

public interface RotulagemServiceInterface {

    DadosDetalhamentoRotulagem register(DadosCadastroRotulagem dados);
}
