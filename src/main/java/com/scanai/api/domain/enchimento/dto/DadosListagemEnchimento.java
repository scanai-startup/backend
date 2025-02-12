package com.scanai.api.domain.enchimento.dto;

import com.scanai.api.domain.enchimento.Enchimento;

import java.time.LocalDateTime;

public record DadosListagemEnchimento(
        Long id,
        float volume,
        LocalDateTime datainiciodespaletizacao,
        LocalDateTime datafimdespaletizacao,
        Boolean conformeosdespaletizacao,
        Boolean ausenciapoeiradespaletizacao,
        Boolean quantidadegarrafasdespaletizacao,
        Boolean coracordodespaletizacao,
        LocalDateTime datainicioenxaguadora,
        LocalDateTime datafimenxaguadora,
        Boolean funcionamentoenxaguadora,
        float pressaoentradaenxaguadora,
        float pressaosaidaenxaguadora,
        Boolean jatopercorreenxaguadora,
        Boolean bicosfuncionandoenxaguadora,
        Boolean ausenciaaguaenxaguadora,
        LocalDateTime datainicioenchedora,
        LocalDateTime datafimenchedora,
        float temperaturaenchedora,
        Boolean nivelmodeloenchedora,
        float pressaoenchedora,
        Boolean rolhaenchedora,
        Boolean qualidaderolhaenchedora,
        Boolean corposestranhos,
        Long fkvinho,
        Long fkrespproducao,
        Long fkrespdespaletizacao,
        Long fkrespenchimento
) {

    public DadosListagemEnchimento(Enchimento dados){
        this(
                dados.getId(),
                dados.getVolume(),
                dados.getDatainiciodespaletizacao(),
                dados.getDatafimdespaletizacao(),
                dados.getConformeosdespaletizacao(),
                dados.getAusenciapoeiradespaletizacao(),
                dados.getQuantidadegarrafasdespaletizacao(),
                dados.getCoracordodespaletizacao(),
                dados.getDatainicioenxaguadora(),
                dados.getDatafimenxaguadora(),
                dados.getFuncionamentoenxaguadora(),
                dados.getPressaoentradaenxaguadora(),
                dados.getPressaosaidaenxaguadora(),
                dados.getJatopercorreenxaguadora(),
                dados.getBicosfuncionandoenxaguadora(),
                dados.getAusenciaaguaenxaguadora(),
                dados.getDatainicioenchedora(),
                dados.getDatafimenchedora(),
                dados.getTemperaturaenchedora(),
                dados.getNivelmodeloenchedora(),
                dados.getPressaoenchedora(),
                dados.getRolhaenchedora(),
                dados.getQualidaderolhaenchedora(),
                dados.getCorposestranhos(),
                dados.getFkvinho(),
                dados.getFkrespproducao(),
                dados.getFkrespdespaletizacao(),
                dados.getFkrespenchimento()
        );

    }
}
