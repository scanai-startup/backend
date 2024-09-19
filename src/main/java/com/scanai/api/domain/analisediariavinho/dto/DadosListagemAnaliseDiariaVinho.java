package com.scanai.api.domain.analisediariavinho.dto;

import com.scanai.api.domain.analisediariavinho.AnaliseDiariaVinho;

import java.util.Date;

public record DadosListagemAnaliseDiariaVinho(
        Long id,
       Long fkvinho,
       Long fkfuncionario,
       float densidade,
       Date data,
       float temperatura,
       float pressao ) {
    public DadosListagemAnaliseDiariaVinho(AnaliseDiariaVinho analiseDiariaVinho){
        this(
                analiseDiariaVinho.getId(),
                analiseDiariaVinho.getFkvinho(),
                analiseDiariaVinho.getFkfuncionario(),
                analiseDiariaVinho.getDensidade(),
                analiseDiariaVinho.getData(),
                analiseDiariaVinho.getTemperatura(),
                analiseDiariaVinho.getPressao()
        );
    }
}
