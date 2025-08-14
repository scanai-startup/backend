package com.scanai.api.domain.liberacao.dto;

import java.time.LocalDate;
import java.util.Date;

public record DadosAtualizarLiberacao(
    Long id,
    int qttproduzida,
    LocalDate datainicio,
    LocalDate datafim,
    int gfs,
    Long fkrotulagem,
    Long fkfuncionario) {
}
