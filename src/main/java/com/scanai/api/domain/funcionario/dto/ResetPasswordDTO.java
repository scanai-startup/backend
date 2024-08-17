package com.scanai.api.domain.funcionario.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordDTO(@NotBlank String senhaAtual, @NotBlank String senhaNova, @NotBlank String matricula) {
}
