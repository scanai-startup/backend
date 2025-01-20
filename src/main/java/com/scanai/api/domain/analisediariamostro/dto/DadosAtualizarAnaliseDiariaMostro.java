package com.scanai.api.domain.analisediariamostro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;
import java.util.Date;

public record DadosAtualizarAnaliseDiariaMostro(
        Long id,
        Long fkmostro,
        Long fkfuncionario,
        float densidade,
        LocalDateTime data,
        float temperatura) {
}
