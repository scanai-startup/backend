package com.scanai.api.domain.analisediariavinho.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record DadosAtualizarAnaliseDiariaVinho(
    Long id,
    Long fkvinho,
    Long fkfuncionario,
    float densidade,
    Date data,
    float temperatura,
    float pressao ) {
}
