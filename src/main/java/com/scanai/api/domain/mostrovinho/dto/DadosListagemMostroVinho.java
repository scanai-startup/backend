package com.scanai.api.domain.mostrovinho.dto;

import com.scanai.api.domain.mostrovinho.MostroVinho;

public record DadosListagemMostroVinho(Long id, Long fkmostro, Long fkvinho) {
    public DadosListagemMostroVinho(MostroVinho mostroVinho) {
        this(
                mostroVinho.getId(),
                mostroVinho.getFkvinho(),
                mostroVinho.getFkmostro()
        );
    }
}
