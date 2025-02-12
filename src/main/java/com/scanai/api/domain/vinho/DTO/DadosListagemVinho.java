package com.scanai.api.domain.vinho.DTO;

import com.scanai.api.domain.vinho.Vinho;

import java.time.LocalDate;
import java.util.Date;

public record DadosListagemVinho(
        Long id,
        LocalDate datafimfermentacao,
        float volume,
        Long fkrotulo,
        Long fkpedecuba
) {

    public DadosListagemVinho(Vinho vinho){
        this(
                vinho.getId(),
                vinho.getDatafimfermentacao(),
                vinho.getVolume(),
                vinho.getFkrotulo(),
                vinho.getFkpedecuba()
        );
    }
}
