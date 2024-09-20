package com.scanai.api.domain.analisediariamostro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record DadosAtualizarAnaliseDiariaMostro(
        Long id,
        Long fkmostro,
        Long fkfuncionario,
        float densidade,
        Date data,
        float temperatura) {
}
