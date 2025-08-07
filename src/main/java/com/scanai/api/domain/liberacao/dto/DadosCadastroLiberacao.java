package com.scanai.api.domain.liberacao.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Date;

public record DadosCadastroLiberacao(@NotNull int qttproduzida,
                                     @PastOrPresent LocalDate datainicio,
                                     @PastOrPresent LocalDate datafim,
                                     @NotNull int gfs,
                                     @NotNull Long fkrotulagem,
                                     @NotNull Long fkfuncionario) {
}
