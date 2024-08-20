package com.scanai.api.domain.rotulo.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroRotulo(@NotBlank String nome, @NotBlank String tipo) {

}
