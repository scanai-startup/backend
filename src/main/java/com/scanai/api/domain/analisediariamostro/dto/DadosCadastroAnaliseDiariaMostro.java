package com.scanai.api.domain.analisediariamostro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record DadosCadastroAnaliseDiariaMostro(
        @NotNull Long fkmostro ,
      @NotNull Long fkfuncionario,
      @NotNull float densidade,
      @NotNull float temperatura) {
}
