package com.scanai.api.domain.analisediariavinho.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record DadosCadastroAnaliseDiariaVinho(
        @NotNull Long fkvinho,
        @NotNull Long fkfuncionario,
        @NotNull float densidade,
        @NotNull float temperatura,
        float pressao //pela regra de negocio, pressao pode ser um campo adicional
) {
}
