package com.scanai.api.domain.analisespedecuba.dto;

import com.scanai.api.domain.analisespedecuba.Analisespedecuba;
import com.scanai.api.domain.higienedeposito.Higienedeposito;

import java.time.LocalDate;

public record ResListAnalisespedecubaDTO(Long fkpedecuba, Long fkfuncionario, float densidade, LocalDate data, int temperatura) {

    public ResListAnalisespedecubaDTO(Analisespedecuba analisespedecuba){
        this(
                analisespedecuba.getFkpedecuba(),
                analisespedecuba.getFkfuncionario(),
                analisespedecuba.getDensidade(),
                analisespedecuba.getData(),
                analisespedecuba.getTemperatura()
        );
    }
}
