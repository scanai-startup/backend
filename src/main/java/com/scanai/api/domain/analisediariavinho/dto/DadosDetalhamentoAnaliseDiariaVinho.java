package com.scanai.api.domain.analisediariavinho.dto;

import com.scanai.api.domain.analisediariavinho.AnaliseDiariaVinho;
import com.scanai.api.domain.vinho.DTO.DadosDetalhamentoVinho;

import java.time.LocalDateTime;
import java.util.Date;

public record DadosDetalhamentoAnaliseDiariaVinho(
        Long id,
      Long fkvinho,
      Long fkfuncionario,
      float densidade,
      LocalDateTime data,
      float temperatura,
      float pressao ) {

    public DadosDetalhamentoAnaliseDiariaVinho(AnaliseDiariaVinho analiseDiariaVinho){
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
