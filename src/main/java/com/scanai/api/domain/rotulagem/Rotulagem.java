package com.scanai.api.domain.rotulagem;

import com.scanai.api.domain.rotulagem.dto.DadosCadastroRotulagem;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Table(name = "tb_rotulagem")
@Entity(name = "tb_rotulagem")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Rotulagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkenchimento;
    
    private Long fkresprotulagem;
    
    private Long fkrespembalamento;
    
    private Long fkrespproducao;

    private LocalDate datainiciorotulagem;
    
    private LocalDate datafimrotulagem;

    private Boolean funcionamentolavadorarotulagem;
    
    private Boolean funcionamentosecadorarotulagem;
    
    private Boolean equipamentosokrotulagem;
    
    private Boolean capsulaacordocapsuladora;
    
    private Boolean capsulagem;
    
    private Boolean defeitoscapsuladora;
    
    private Boolean materiaisrotuladora;
    
    private Boolean defeitosvisuaisrotuladora;
    
    private Boolean descricaorotuladora;
    
    private Boolean marcacaorotuladora;
    
    private Boolean imagemrotuladora;
    
    private Boolean caixaacordoembaladora;
    
    private Boolean separadoresembaladora;
    
    private Boolean colocacaoseparadoresembaladora;
    
    private Boolean selagemembaladora;
    
    private Boolean marcacaoembaladora;
    
    private Boolean humidadepaletizadora;
    
    private Boolean dossierpaletizadora;
    
    private Boolean identificacaopaletizadora;

    private int qttcaixaspaletizadora;

    public Rotulagem(DadosCadastroRotulagem data) {
        this.fkenchimento = data.fkEnchimento();
        this.fkresprotulagem = data.fkRespRotulagem();
        this.fkrespembalamento = data.fkRespEmbalamento();
        this.fkrespproducao = data.fkRespProducao();
        this.datainiciorotulagem = data.dataInicioRotulagem();
        this.datafimrotulagem = data.dataFimRotulagem();
        this.funcionamentolavadorarotulagem = data.funcionamentoLavadoraRotulagem();
        this.funcionamentosecadorarotulagem = data.funcionamentoSecadoraRotulagem();
        this.equipamentosokrotulagem = data.equipamentosOkRotulagem();
        this.capsulaacordocapsuladora = data.capsulaAcordoCapsuladora();
        this.capsulagem = data.capsulagem();
        this.defeitoscapsuladora = data.defeitosCapsuladora();
        this.materiaisrotuladora = data.materiaisRotuladora();
        this.defeitosvisuaisrotuladora = data.defeitosVisuaisRotuladora();
        this.descricaorotuladora = data.descricaoRotuladora();
        this.marcacaorotuladora = data.marcacaoRotuladora();
        this.imagemrotuladora = data.imagemRotuladora();
        this.caixaacordoembaladora = data.caixaAcordoEmbaladora();
        this.separadoresembaladora = data.separadoresEmbaladora();
        this.colocacaoseparadoresembaladora = data.colocacaoSeparadoresEmbaladora();
        this.selagemembaladora = data.selagemEmbaladora();
        this.marcacaoembaladora = data.marcacaoEmbaladora();
        this.humidadepaletizadora = data.humidadePaletizadora();
        this.dossierpaletizadora = data.dossierPaletizadora();
        this.qttcaixaspaletizadora = data.qttCaixasPaletizadora();
        this.identificacaopaletizadora = data.identificacaoPaletizadora();
    }

}
