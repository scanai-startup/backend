package com.scanai.api.domain.deposito.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosTrasfegaDeposito(@NotNull String tipo, @NotNull Long idLiquidoOrigem, @NotNull Long idDepositoDestino, @NotNull Long fkfuncionario, float volumetrasfega, float volumechegada) {
}
