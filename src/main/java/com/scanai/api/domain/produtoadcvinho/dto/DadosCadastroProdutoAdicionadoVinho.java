package com.scanai.api.domain.produtoadcvinho.dto;

import com.scanai.api.domain.produtoadcpedecuba.UnidadeDeMedida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProdutoAdicionadoVinho(@NotNull Long fkvinho, @NotBlank String nome, @NotNull int quantidade, @NotNull
                                                  UnidadeDeMedida unidade) {
}
