package com.scanai.api.domain.vinculodepositovinho.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record DadosCadastroVinculoDepositoVinho(@NotNull Long depositoId, @FutureOrPresent Date dataFimFermentacao, @NotNull Long pedecubaId, Float pedecubaVolumePerda, @NotNull Long rotuloId, @NotNull Long mostroId, @NotNull Float volumeMostro, @NotNull Float volumeMostroPerda, @NotNull Long funcionarioId) {
}
