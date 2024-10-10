package com.scanai.api.domain.material.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroMaterial(@NotNull String nome, @NotNull int quantidade) {
}
