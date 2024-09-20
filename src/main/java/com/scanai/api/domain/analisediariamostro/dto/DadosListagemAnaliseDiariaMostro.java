package com.scanai.api.domain.analisediariamostro.dto;


import com.scanai.api.domain.analisediariamostro.AnaliseDiariaMostro;

import java.util.Date;

public record DadosListagemAnaliseDiariaMostro(
        Long id,
        Long fkmostro,
        Long fkfuncionario,
        float densidade,
        Date data,
        float temperatura) {

    public DadosListagemAnaliseDiariaMostro(AnaliseDiariaMostro analiseDiariaMostro){
        this(
                analiseDiariaMostro.getId(),
                analiseDiariaMostro.getFkmostro(),
                analiseDiariaMostro.getFkfuncionario(),
                analiseDiariaMostro.getDensidade(),
                analiseDiariaMostro.getData(),
                analiseDiariaMostro.getTemperatura()
        );
    }
}

