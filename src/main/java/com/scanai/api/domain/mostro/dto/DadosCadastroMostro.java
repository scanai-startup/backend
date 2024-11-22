package com.scanai.api.domain.mostro.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroMostro(@NotNull Long fkfuncionario, @NotNull int volume, Long fkmostro1, Long fkmostro2) {
}
