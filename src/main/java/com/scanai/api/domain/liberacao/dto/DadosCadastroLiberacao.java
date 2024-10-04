package com.scanai.api.domain.liberacao.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record DadosCadastroLiberacao(@NotNull int qttproduzida,
                                     @PastOrPresent Date datainicio,
                                     @PastOrPresent Date datafim,
                                     @NotNull int gfs,
                                     @NotNull int fkrotulagem,
                                     @NotNull int fkfuncionario) {
}
