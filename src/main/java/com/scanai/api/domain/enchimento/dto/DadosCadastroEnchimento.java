package com.scanai.api.domain.enchimento.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record DadosCadastroEnchimento(
        float volume,
        @PastOrPresent LocalDateTime datainiciodespaletizacao,
        @Future LocalDateTime datafimdespaletizacao,
        @NotNull  Boolean conformeosdespaletizacao,
        @NotNull Boolean ausenciapoeiradespaletizacao,
        @NotNull Boolean quantidadegarrafasdespaletizacao,
        @NotNull Boolean coracordodespaletizacao,
        @PastOrPresent LocalDateTime datainicioenxaguadora,
        @Future LocalDateTime datafimenxaguadora,
        @NotNull Boolean funcionamentoenxaguadora,
        @NotNull float pressaoentradaenxaguadora,
        @NotNull float pressaosaidaenxaguadora,
        @NotNull Boolean jatopercorreenxaguadora,
        @NotNull Boolean bicosfuncionandoenxaguadora,
        @NotNull Boolean ausenciaaguaenxaguadora,
        @PastOrPresent LocalDateTime datainicioenchedora,
        @Future LocalDateTime datafimenchedora,
        @NotNull float temperaturaenchedora,
        @NotNull Boolean nivelmodeloenchedora,
        @NotNull float pressaoenchedora,
        @NotNull Boolean rolhaenchedora,
        @NotNull Boolean qualidaderolhaenchedora,
        @NotNull Boolean corposestranhos,
        @NotNull Long fkvinho,
        @NotNull Long fkrespproducao,
        @NotNull Long fkrespdespaleticacao,
        @NotNull Long fkrespenchimento) {

}
