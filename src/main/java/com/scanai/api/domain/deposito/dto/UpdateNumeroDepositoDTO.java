package com.scanai.api.domain.deposito.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateNumeroDepositoDTO(@NotBlank String numeroAtual, @NotBlank String numeroNovo) {
}
