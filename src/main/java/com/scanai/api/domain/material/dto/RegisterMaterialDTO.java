package com.scanai.api.domain.material.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterMaterialDTO(@NotNull String nome, @NotNull int quantidade) {
}
