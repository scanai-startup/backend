package com.scanai.api.domain.entradamaterial.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Date;

public record DadosCadastroEntradaMaterial(
        @NotNull Long fkmaterial,
        @NotNull int qttentrada,
        @NotNull float valorunidade,
        @NotNull @PastOrPresent LocalDate dataentrada,
        @NotNull Long fklotematerial) {
}
