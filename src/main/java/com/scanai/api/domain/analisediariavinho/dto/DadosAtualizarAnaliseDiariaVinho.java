package com.scanai.api.domain.analisediariavinho.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;
import java.util.Date;

public record DadosAtualizarAnaliseDiariaVinho(
    Long id,
    Long fkvinho,
    Long fkfuncionario,
    float densidade,
    float temperatura,
    float pressao ) {
}
