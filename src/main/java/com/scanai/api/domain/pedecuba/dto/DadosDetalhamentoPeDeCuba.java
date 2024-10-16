package com.scanai.api.domain.pedecuba.dto;

import com.scanai.api.domain.pedecuba.Pedecuba;

import java.time.LocalDate;

public record DadosDetalhamentoPeDeCuba(Long id, Long fkfuncionario, LocalDate datafimfermentacao, LocalDate datainicio, int volume, boolean valid) {
    public DadosDetalhamentoPeDeCuba(Pedecuba pedecuba) {
        this(
                pedecuba.getId(),
                pedecuba.getFkfuncionario(),
                pedecuba.getDatafimfermentacao(),
                pedecuba.getDatainicio(),
                pedecuba.getVolume(),
                pedecuba.isValid()
        );
    }
}
