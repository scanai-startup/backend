package com.scanai.api.domain.analisespedecuba.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterAnalisepedecubaDTO(@NotNull Long fkpedecuba,@NotNull Long fkfuncionario,@NotNull float densidade,@NotNull LocalDate data, @NotNull int temperatura) {
}
