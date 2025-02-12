package com.scanai.api.domain.vinho.DTO;

import com.scanai.api.domain.vinho.Vinho;

import java.time.LocalDate;
import java.util.Date;

public record DadosDetalhamentoVinho(Long id, LocalDate datafimfermentacao, Float volume, Long fkrotulo, Long fkpedecuba) {

    public DadosDetalhamentoVinho(Vinho vinho) {
        this(
                vinho.getId(),
                vinho.getDatafimfermentacao(),
                vinho.getVolume(),
                vinho.getFkrotulo(),
                vinho.getFkpedecuba()
        );
    }
}