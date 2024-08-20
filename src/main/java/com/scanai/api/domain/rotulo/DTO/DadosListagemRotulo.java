package com.scanai.api.domain.rotulo.DTO;

import com.scanai.api.domain.rotulo.Rotulo;

import java.util.Date;

public record DadosListagemRotulo(
        Long id,
        String nome,
        String tipo
) {

    public DadosListagemRotulo(Rotulo rotulo){
        this(
                rotulo.getId(), rotulo.getNome(), rotulo.getTipo()
        );
    }

}
