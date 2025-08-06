package com.scanai.api.domain.enchimento;


import com.scanai.api.domain.enchimento.dto.DadosAtualizarEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosCadastroEnchimento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    private Long fkvinho;
    private Long fkrespproducao;
    private Long fkrespdespaletizacao;
    private Long fkrespenchimento;

    public Enchimento(DadosCadastroEnchimento dados) {
        this.volume = dados.volumeTrasfega() - dados.volumeChegada();
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
        this.fkrespdespaletizacao = dados.fkrespdespaletizacao();
        this.fkrespenchimento = dados.fkrespenchimento();
    }

    public void atualizar(DadosAtualizarEnchimento dados){
            this.volume = dados.volumeTrasfega() - dados.volumeChegada();
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
            this.fkrespdespaletizacao = dados.fkrespdespaletizacao();
            this.fkrespenchimento = dados.fkrespenchimento();
    }


}
