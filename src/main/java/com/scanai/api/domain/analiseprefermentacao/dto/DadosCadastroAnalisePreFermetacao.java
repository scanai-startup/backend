package com.scanai.api.domain.analiseprefermentacao.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroAnalisePreFermetacao(@NotNull LocalDate data, @NotNull Long fkfuncionario, @NotNull Long fkvinho, @NotNull float atotal, @NotNull float acucarRed, @NotNull int ph, @NotNull float so2l, @NotNull float ta) {
}
