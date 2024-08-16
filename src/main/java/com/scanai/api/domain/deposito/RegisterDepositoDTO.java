package com.scanai.api.domain.deposito;

import jakarta.validation.constraints.NotBlank;

public record RegisterDepositoDTO(@NotBlank String tipodeposito, @NotBlank String numerodeposito) {
}
