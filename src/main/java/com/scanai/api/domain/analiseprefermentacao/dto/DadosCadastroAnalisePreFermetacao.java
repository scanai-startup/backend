package com.scanai.api.domain.analiseprefermentacao.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroAnalisePreFermetacao(@NotNull Long fkfuncionario, @NotNull Long fkvinho, @NotNull float atotal, @NotNull float acucarRed, @NotNull int ph, @NotNull float so2l, @NotNull float ta) {
}
