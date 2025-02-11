package com.scanai.api.domain.vinho.DTO;

import com.scanai.api.domain.vinho.Vinho;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroVinho(@NotNull Long fkmostro, @NotNull Float volume, @NotNull Long fkrotulo, @NotNull Long fkpedecuba) {

    public DadosCadastroVinho(Vinho vinho) {
        this(vinho.getFkmostro(), vinho.getVolume(), vinho.getFkrotulo(), vinho.getFkpedecuba());
    }
}
