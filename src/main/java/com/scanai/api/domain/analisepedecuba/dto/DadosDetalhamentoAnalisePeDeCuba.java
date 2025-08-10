package com.scanai.api.domain.analisepedecuba.dto;

import com.scanai.api.domain.analisepedecuba.AnalisePeDeCubaService;

import java.time.LocalDateTime;

public record DadosDetalhamentoAnalisePeDeCuba(Long id, Long fkpedecuba, Long fkfuncionario, Float densidade, LocalDateTime data, Float temperatura) {
    public DadosDetalhamentoAnalisePeDeCuba(AnalisePeDeCubaService analisePeDeCubaService) {
        this(
                analisePeDeCubaService.getId(),
                analisePeDeCubaService.getFkpedecuba(),
                analisePeDeCubaService.getFkfuncionario(),
                analisePeDeCubaService.getDensidade(),
                analisePeDeCubaService.getData(),
                analisePeDeCubaService.getTemperatura()
        );
    }
}
