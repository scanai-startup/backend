package com.scanai.api.domain.viticultor.DTO;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroViticultor(@NotBlank String cpf, @NotBlank String nome) {
}
