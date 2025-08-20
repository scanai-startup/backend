package com.scanai.api.domain.analisediariavinho.dto;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record DadosCadastroAnaliseDiariaVinho(
        @NotNull Long fkvinho,
        @NotNull Long fkfuncionario,
        @NotNull float densidade,
        @NotNull float temperatura,
        float pressao, //pela regra de negocio, pressao pode ser um campo adicional
        @PastOrPresent LocalDateTime data
) {
}
