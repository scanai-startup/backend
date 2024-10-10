package com.scanai.api.domain.depositovinho.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroDepositoVinho(@NotNull Long fkvinho, @NotNull Long fkdeposito, @NotNull LocalDate datainicio, @NotNull Long fkfuncionario) {
}
