package com.scanai.api.domain.lotematerial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroLoteMaterial(@NotNull Long fkmaterial, @NotBlank String fornecedor, @NotBlank String numerolote) {
}
