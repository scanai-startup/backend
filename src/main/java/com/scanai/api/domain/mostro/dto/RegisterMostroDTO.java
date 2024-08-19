package com.scanai.api.domain.mostro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterMostroDTO(@NotNull LocalDate fimfermentacao, @NotNull Long fkfuncionario, @NotNull int volume) {
}
