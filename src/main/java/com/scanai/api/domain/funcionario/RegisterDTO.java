package com.scanai.api.domain.funcionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotBlank String matricula, @NotBlank String senha, @NotNull FuncionarioRole role, @NotBlank String nome, @NotBlank String email) {
}
