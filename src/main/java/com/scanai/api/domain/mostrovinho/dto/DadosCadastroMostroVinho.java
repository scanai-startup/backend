package com.scanai.api.domain.mostrovinho.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroMostroVinho(@NotNull Long fkmostro, @NotNull Long fkvinho) {
}
