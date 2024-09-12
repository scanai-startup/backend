package com.scanai.api.domain.analisepedecuba.dto;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;

import java.time.LocalDate;

public record ResListAnalisespedecubaDTO(Long fkpedecuba, Long fkfuncionario, float densidade, LocalDate data, int temperatura) {

    public ResListAnalisespedecubaDTO(Analisepedecuba analisespedecuba){
        this(
                analisespedecuba.getFkpedecuba(),
                analisespedecuba.getFkfuncionario(),
                analisespedecuba.getDensidade(),
                analisespedecuba.getData(),
                analisespedecuba.getTemperatura()
        );
    }
}
