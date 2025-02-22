package com.scanai.api.domain.pedecuba.dto;

import com.scanai.api.domain.pedecuba.Pedecuba;

import java.time.LocalDate;

public record DadosDetalhamentoPeDeCuba(Long id, Long fkfuncionario, Long fkpedecuba, LocalDate datafimfermentacao, LocalDate datainicio, float volume, boolean valid) {
    public DadosDetalhamentoPeDeCuba(Pedecuba pedecuba) {
        this(
                pedecuba.getId(),
                pedecuba.getFkfuncionario(),
                pedecuba.getFkpedecuba(),
                pedecuba.getDatafimfermentacao(),
                pedecuba.getDatainicio(),
                pedecuba.getVolume(),
                pedecuba.isValid()
        );
    }
}
