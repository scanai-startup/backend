package com.scanai.api.domain.funcionario.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(@NotBlank String matricula,@NotBlank String senha) {
}
