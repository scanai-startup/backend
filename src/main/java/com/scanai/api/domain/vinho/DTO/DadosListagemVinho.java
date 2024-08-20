package com.scanai.api.domain.vinho.DTO;

import com.scanai.api.domain.vinho.Vinho;

import java.util.Date;

public record DadosListagemVinho(
        Long id,
        Date datafimfermentacao,
        float volume
) {

    public DadosListagemVinho(Vinho vinho){
        this(
                vinho.getId(), vinho.getDatafimfermentacao(), vinho.getVolume()
        );
    }
}
