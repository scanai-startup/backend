package com.scanai.api.domain.mostrovinho.dto;

import com.scanai.api.domain.mostrovinho.MostroVinho;

public record DadosDetalhamentoMostroVinho(Long id, Long fkmostro, Long fkvinho) {
    public DadosDetalhamentoMostroVinho(MostroVinho mostroVinho) {
        this(
                mostroVinho.getId(),
                mostroVinho.getFkvinho(),
                mostroVinho.getFkmostro()
        );
    }
}
