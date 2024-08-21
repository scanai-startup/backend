package com.scanai.api.domain.viticultor.DTO;

import jakarta.validation.constraints.NotBlank;

public record DadosCadatroViticultor(@NotBlank String cpf, @NotBlank String nome) {
}
