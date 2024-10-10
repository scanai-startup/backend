package com.scanai.api.domain.depositomostro.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroDepositoMostro(@NotNull Long fkmostro, @NotNull Long fkdeposito, @NotNull LocalDate datainicio, @NotNull Long fkfuncionario) {
}
