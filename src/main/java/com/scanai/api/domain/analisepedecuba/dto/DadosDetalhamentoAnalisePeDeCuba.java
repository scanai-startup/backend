package com.scanai.api.domain.analisepedecuba.dto;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosDetalhamentoAnalisePeDeCuba(Long id, Long fkpedecuba, Long fkfuncionario, Float densidade, LocalDateTime data, Float temperatura) {
    public DadosDetalhamentoAnalisePeDeCuba(Analisepedecuba analisepedecuba) {
        this(
                analisepedecuba.getId(),
                analisepedecuba.getFkpedecuba(),
                analisepedecuba.getFkfuncionario(),
                analisepedecuba.getDensidade(),
                analisepedecuba.getData(),
                analisepedecuba.getTemperatura()
        );
    }
}
