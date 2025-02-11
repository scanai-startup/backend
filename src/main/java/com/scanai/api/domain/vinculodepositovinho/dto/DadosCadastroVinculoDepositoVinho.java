package com.scanai.api.domain.vinculodepositovinho.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record DadosCadastroVinculoDepositoVinho(@NotNull Long depositoId, @NotNull Long pedecubaId, @NotNull Float volumeTrasfegaPedecuba, @NotNull Float volumeChegadaPedecuba, @NotNull Long rotuloId, @NotNull Long mostroId,  @NotNull Float volumeTrasfegaMostro, @NotNull Float volumeChegadaMostro, @NotNull Long funcionarioId) {
}
