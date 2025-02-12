package com.scanai.api.domain.enchimento.dto;

import java.time.LocalDateTime;

public record DadosAtualizarEnchimento(
        Long id,
        float volumeTrasfega,
        float volumeChegada,
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
}
