package com.scanai.api.domain.enchimento;


import com.scanai.api.domain.enchimento.dto.DadosAtualizarEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosCadastroEnchimento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "tb_enchimento")
@Entity(name = "tb_enchimento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Enchimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float volume;
    private LocalDateTime datainiciodespaletizacao;
    private LocalDateTime datafimdespaletizacao;
    private Boolean conformeosdespaletizacao;
    private Boolean ausenciapoeiradespaletizacao;
    private Boolean quantidadegarrafasdespaletizacao;
    private Boolean coracordodespaletizacao;
    private LocalDateTime datainicioenxaguadora;
    private LocalDateTime datafimenxaguadora;
    private Boolean funcionamentoenxaguadora;
    private float pressaoentradaenxaguadora;
    private float pressaosaidaenxaguadora;
    private Boolean jatopercorreenxaguadora;
    private Boolean bicosfuncionandoenxaguadora;
    private Boolean ausenciaaguaenxaguadora;
    private LocalDateTime datainicioenchedora;
    private LocalDateTime datafimenchedora;
    private float temperaturaenchedora;
    private Boolean nivelmodeloenchedora;
    private float pressaoenchedora;
    private Boolean rolhaenchedora;
    private Boolean qualidaderolhaenchedora;
    private Boolean corposestranhos;



    // chaves estrangeiras
    private Long fkvinho;
    private Long fkrespproducao;
    private Long fkrespdespaleticacao;
    private Long fkrespenchimento;

    public Enchimento(DadosCadastroEnchimento dados) {
        this.volume = dados.volume();
        this.datainiciodespaletizacao = dados.datainiciodespaletizacao();
        this.datafimdespaletizacao = dados.datafimdespaletizacao();
        this.conformeosdespaletizacao = dados.conformeosdespaletizacao();
        this.ausenciapoeiradespaletizacao = dados.ausenciapoeiradespaletizacao();
        this.quantidadegarrafasdespaletizacao = dados.quantidadegarrafasdespaletizacao();
        this.coracordodespaletizacao = dados.coracordodespaletizacao();
        this.datainicioenxaguadora = dados.datainicioenxaguadora();
        this.datafimenxaguadora = dados.datafimenxaguadora();
        this.funcionamentoenxaguadora = dados.funcionamentoenxaguadora();
        this.pressaoentradaenxaguadora = dados.pressaoentradaenxaguadora();
        this.pressaosaidaenxaguadora = dados.pressaosaidaenxaguadora();
        this.jatopercorreenxaguadora = dados.jatopercorreenxaguadora();
        this.bicosfuncionandoenxaguadora = dados.bicosfuncionandoenxaguadora();
        this.ausenciaaguaenxaguadora = dados.ausenciaaguaenxaguadora();
        this.datainicioenchedora = dados.datainicioenchedora();
        this.datafimenchedora = dados.datafimenchedora();
        this.temperaturaenchedora = dados.temperaturaenchedora();
        this.nivelmodeloenchedora = dados.nivelmodeloenchedora();
        this.pressaoenchedora = dados.pressaoenchedora();
        this.rolhaenchedora = dados.rolhaenchedora();
        this.qualidaderolhaenchedora = dados.qualidaderolhaenchedora();
        this.corposestranhos = dados.corposestranhos();
        this.fkvinho = dados.fkvinho();
        this.fkrespproducao = dados.fkrespproducao();
        this.fkrespdespaleticacao = dados.fkrespdespaleticacao();
        this.fkrespenchimento = dados.fkrespenchimento();
    }

    public void atualizar(DadosAtualizarEnchimento dados){
        if (dados.volume() != 0) {
            this.volume = dados.volume();
        }
        if (dados.datainiciodespaletizacao() != null) {
            this.datainiciodespaletizacao = dados.datainiciodespaletizacao();
        }
        if (dados.datafimdespaletizacao() != null) {
            this.datafimdespaletizacao = dados.datafimdespaletizacao();
        }
        if (dados.conformeosdespaletizacao() != null) {
            this.conformeosdespaletizacao = dados.conformeosdespaletizacao();
        }
        if (dados.ausenciapoeiradespaletizacao() != null) {
            this.ausenciapoeiradespaletizacao = dados.ausenciapoeiradespaletizacao();
        }
        if (dados.quantidadegarrafasdespaletizacao() != null) {
            this.quantidadegarrafasdespaletizacao = dados.quantidadegarrafasdespaletizacao();
        }
        if (dados.coracordodespaletizacao() != null) {
            this.coracordodespaletizacao = dados.coracordodespaletizacao();
        }
        if (dados.datainicioenxaguadora() != null) {
            this.datainicioenxaguadora = dados.datainicioenxaguadora();
        }
        if (dados.datafimenxaguadora() != null) {
            this.datafimenxaguadora = dados.datafimenxaguadora();
        }
        if (dados.funcionamentoenxaguadora() != null) {
            this.funcionamentoenxaguadora = dados.funcionamentoenxaguadora();
        }
        if (dados.pressaoentradaenxaguadora() != 0) {
            this.pressaoentradaenxaguadora = dados.pressaoentradaenxaguadora();
        }
        if (dados.pressaosaidaenxaguadora() != 0) {
            this.pressaosaidaenxaguadora = dados.pressaosaidaenxaguadora();
        }
        if (dados.jatopercorreenxaguadora() != null) {
            this.jatopercorreenxaguadora = dados.jatopercorreenxaguadora();
        }
        if (dados.bicosfuncionandoenxaguadora() != null) {
            this.bicosfuncionandoenxaguadora = dados.bicosfuncionandoenxaguadora();
        }
        if (dados.ausenciaaguaenxaguadora() != null) {
            this.ausenciaaguaenxaguadora = dados.ausenciaaguaenxaguadora();
        }
        if (dados.datainicioenchedora() != null) {
            this.datainicioenchedora = dados.datainicioenchedora();
        }
        if (dados.datafimenchedora() != null) {
            this.datafimenchedora = dados.datafimenchedora();
        }
        if (dados.temperaturaenchedora() != 0) {
            this.temperaturaenchedora = dados.temperaturaenchedora();
        }
        if (dados.nivelmodeloenchedora() != null) {
            this.nivelmodeloenchedora = dados.nivelmodeloenchedora();
        }
        if (dados.pressaoenchedora() != 0) {
            this.pressaoenchedora = dados.pressaoenchedora();
        }
        if (dados.rolhaenchedora() != null) {
            this.rolhaenchedora = dados.rolhaenchedora();
        }
        if (dados.qualidaderolhaenchedora() != null) {
            this.qualidaderolhaenchedora = dados.qualidaderolhaenchedora();
        }
        if (dados.corposestranhos() != null) {
            this.corposestranhos = dados.corposestranhos();
        }
        if (dados.fkvinho() != null) {
            this.fkvinho = dados.fkvinho();
        }
        if (dados.fkrespproducao() != null) {
            this.fkrespproducao = dados.fkrespproducao();
        }
        if (dados.fkrespdespaleticacao() != null) {
            this.fkrespdespaleticacao = dados.fkrespdespaleticacao();
        }
        if (dados.fkrespenchimento() != null) {
            this.fkrespenchimento = dados.fkrespenchimento();
        }

    }


}
