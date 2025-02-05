package com.scanai.api.domain.lotematerial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroLoteMaterial(@NotBlank String fornecedor, @NotBlank String numerolote) {
}
