package com.scanai.api.domain.produtoadcpedecuba.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterProdutoadcpedecubaDTO(@NotNull Long fkpedecuba, @NotNull String nome, @NotNull int quantidade) {
}
