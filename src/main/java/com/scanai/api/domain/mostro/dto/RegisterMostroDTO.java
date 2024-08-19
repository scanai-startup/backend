package com.scanai.api.domain.mostro.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterMostroDTO(@NotNull Long fkfuncionario, @NotNull int volume) {
}
