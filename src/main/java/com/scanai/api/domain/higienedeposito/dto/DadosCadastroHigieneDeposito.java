package com.scanai.api.domain.higienedeposito.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroHigieneDeposito(@NotNull LocalDate datahigiene, @NotNull Long fkdeposito) {
}
