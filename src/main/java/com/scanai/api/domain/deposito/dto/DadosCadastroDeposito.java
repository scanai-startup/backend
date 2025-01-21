package com.scanai.api.domain.deposito.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroDeposito(@NotBlank String tipodeposito, @NotBlank String numerodeposito, @NotNull float capacidade) {
}
