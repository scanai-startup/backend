package com.scanai.api.domain.analisepedecuba.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosCadastroAnalisePeDeCuba(@NotNull Long fkpedecuba, @NotNull Long fkfuncionario, @NotNull Float densidade, @NotNull Float temperatura, @PastOrPresent LocalDateTime data) {
}
