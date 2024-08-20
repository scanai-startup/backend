package com.scanai.api.domain.vinho.DTO;

import com.scanai.api.domain.vinho.Vinho;

import java.util.Date;

public record DadosListagemVinho(
        Long id,
        Date datafimfermentacao,
        float volume,
        Long fkrotulo,
        Long fkmostro,
        Long fkpedecuba
) {

    public DadosListagemVinho(Vinho vinho){
        this(
                vinho.getId(),
                vinho.getDatafimfermentacao(),
                vinho.getVolume(),
                vinho.getFkrotulo(),
                vinho.getFkmostro(),
                vinho.getFkpedecuba()
        );
    }
}
