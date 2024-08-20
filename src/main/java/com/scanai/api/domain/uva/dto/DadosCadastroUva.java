package com.scanai.api.domain.uva.dto;

import jakarta.validation.constraints.*;

import java.util.Date;

public record DadosCadastroUva(
        //colocar as paradas aqui
        @PastOrPresent
        Date datachegada,
        @NotNull
        int numerotalao,
        @NotNull
        int sanidade,
        @NotNull
        int peso,
        @NotBlank
        String so2,
        @NotNull
        int numerolote,
        @NotBlank
        String tipodevinho,
        @NotBlank
        String casta,
        @NotBlank
        Long fkviticultor,
        @NotBlank
        Long fkfuncionario
) {
}
