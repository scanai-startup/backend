package com.scanai.api.domain.analisepedecuba.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroAnalisePeDeCuba(@NotNull Long fkpedecuba, @NotNull Long fkfuncionario, @NotNull float densidade, @NotNull LocalDate data, @NotNull int temperatura) {
}
