package com.scanai.api.domain.analisepedecuba.dto;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;

import java.time.LocalDateTime;

public record DadosDetalhamentoAnalisePeDeCuba(Long id, Long fkpedecuba, Long fkfuncionario, Float densidade, LocalDateTime data, Float temperatura) {
    public DadosDetalhamentoAnalisePeDeCuba(Analisepedecuba analisePeDeCuba) {
        this(
                analisePeDeCuba.getId(),
                analisePeDeCuba.getFkpedecuba(),
                analisePeDeCuba.getFkfuncionario(),
                analisePeDeCuba.getDensidade(),
                analisePeDeCuba.getData(),
                analisePeDeCuba.getTemperatura()
        );
    }
}
