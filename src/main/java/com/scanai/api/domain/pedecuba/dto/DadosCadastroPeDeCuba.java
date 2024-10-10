package com.scanai.api.domain.pedecuba.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroPeDeCuba(@NotNull Long fkfuncionario, @NotNull LocalDate datainicio, @NotNull int volume) {
}
