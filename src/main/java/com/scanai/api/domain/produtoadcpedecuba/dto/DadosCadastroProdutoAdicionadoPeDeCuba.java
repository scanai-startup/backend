package com.scanai.api.domain.produtoadcpedecuba.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProdutoAdicionadoPeDeCuba(@NotNull Long fkpedecuba, @NotBlank String nome, @NotNull int quantidade) {
}
