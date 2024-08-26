package com.scanai.api.domain.analiseprefermentacao.dto;

import com.scanai.api.domain.funcionario.FuncionarioRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotNull Long fkfuncionario,@NotNull Long fkvinho,@NotNull float atotal,@NotNull float acucarRed,@NotNull int ph,@NotNull float so2l,@NotNull float ta) {
}
