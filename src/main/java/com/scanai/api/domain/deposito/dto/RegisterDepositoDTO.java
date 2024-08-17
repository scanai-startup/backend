package com.scanai.api.domain.deposito.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterDepositoDTO(@NotBlank String tipodeposito, @NotBlank String numerodeposito) {
}
