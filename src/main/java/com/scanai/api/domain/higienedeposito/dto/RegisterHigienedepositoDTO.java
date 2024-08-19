package com.scanai.api.domain.higienedeposito.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterHigienedepositoDTO(@NotNull LocalDate datahigiene, @NotNull Long fkdeposito) {
}
