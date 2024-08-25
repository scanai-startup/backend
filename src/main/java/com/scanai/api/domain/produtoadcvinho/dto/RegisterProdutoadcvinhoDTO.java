package com.scanai.api.domain.produtoadcvinho.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterProdutoadcvinhoDTO(@NotNull Long fkvinho, @NotBlank String nome, @NotNull int quantidade) {
}
