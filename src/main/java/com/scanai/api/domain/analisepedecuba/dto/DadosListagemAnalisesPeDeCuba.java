package com.scanai.api.domain.analisepedecuba.dto;

import com.scanai.api.domain.analisepedecuba.AnalisePeDeCubaService;

import java.time.LocalDateTime;

public record DadosListagemAnalisesPeDeCuba(Long fkpedecuba, Long fkfuncionario, Float densidade, LocalDateTime data, Float temperatura) {

    public DadosListagemAnalisesPeDeCuba(AnalisePeDeCubaService analisespedecuba){
        this(
                analisespedecuba.getFkpedecuba(),
                analisespedecuba.getFkfuncionario(),
                analisespedecuba.getDensidade(),
                analisespedecuba.getData(),
                analisespedecuba.getTemperatura()
        );
    }
}
