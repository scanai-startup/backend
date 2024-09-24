package com.scanai.api.domain.lotematerial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterLotematerialDTO(@NotBlank String fornecedor, @NotBlank String numerolote, @NotNull Long fkmaterial) {
}
