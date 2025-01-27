package com.scanai.api.domain.analisediariamostro.dto;


import com.scanai.api.domain.analisediariamostro.AnaliseDiariaMostro;

import java.time.LocalDateTime;
import java.util.Date;

public record DadosDetalhamentoAnaliseDiariaMostro(
        Long id,
        Long fkmostro,
        Long fkfuncionario,
        float densidade,
        LocalDateTime data,
        float temperatura) {

    public DadosDetalhamentoAnaliseDiariaMostro(AnaliseDiariaMostro analiseDiariaMostro){
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

