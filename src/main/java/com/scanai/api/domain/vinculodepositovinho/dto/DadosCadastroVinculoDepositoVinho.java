package com.scanai.api.domain.vinculodepositovinho.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record DadosCadastroVinculoDepositoVinho(@NotNull Long depositoId, @FutureOrPresent Date dataFimFermentacao, @NotNull Long pedecubaId, @NotNull Long rotuloId, @NotNull List<Long> mostroIds, @NotNull Long funcionarioId) {
}
