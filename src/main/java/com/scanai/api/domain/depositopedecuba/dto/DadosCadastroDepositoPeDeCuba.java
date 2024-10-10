package com.scanai.api.domain.depositopedecuba.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroDepositoPeDeCuba(@NotNull Long fkpedecuba, @NotNull Long fkdeposito, @NotNull LocalDate datainicio, @NotNull Long fkfuncionario) {
}
