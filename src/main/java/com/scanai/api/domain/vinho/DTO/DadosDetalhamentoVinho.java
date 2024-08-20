package com.scanai.api.domain.vinho.DTO;

import com.scanai.api.domain.vinho.Vinho;

import java.util.Date;

public record DadosDetalhamentoVinho(Long id, Date datafimfermentacao, Float volume) {

    public DadosDetalhamentoVinho(Vinho vinho) {
        this(
                vinho.getId(),
                vinho.getDatafimfermentacao(),
                vinho.getVolume()
        );
    }
}