package com.scanai.api.domain.analisepedecuba.dto;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosListagemAnalisesPeDeCuba(Long fkpedecuba, Long fkfuncionario, Float densidade, LocalDateTime data, Float temperatura) {

    public DadosListagemAnalisesPeDeCuba(Analisepedecuba analisespedecuba){
        this(
                analisespedecuba.getFkpedecuba(),
                analisespedecuba.getFkfuncionario(),
                analisespedecuba.getDensidade(),
                analisespedecuba.getData(),
                analisespedecuba.getTemperatura()
        );
    }
}
