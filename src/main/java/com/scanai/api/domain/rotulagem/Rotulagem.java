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

    private Long fkEnchimento;
    private Long fkRespRotulagem;
    private Long fkRespEmbalamento;
    private Long fkRespProducao;

    private LocalDate dataInicioRotulagem;
    private LocalDate dataFimRotulagem;

    private Boolean funcionamentoLavadoraRotulagem;
    private Boolean funcionamentoSecadoraRotulagem;
    private Boolean equipamentosOkRotulagem;
    private Boolean capsulaAcordoCapsuladora;
    private Boolean capsulagem;
    private Boolean defeitosCapsuladora;
    private Boolean materiaisRotuladora;
    private Boolean defeitosVisuaisRotuladora;
    private Boolean descricaoRotuladora;
    private Boolean marcacaoRotuladora;
    private Boolean imagemRotuladora;
    private Boolean caixaAcordoEmbaladora;
    private Boolean separadoresEmbaladora;
    private Boolean colocacaoSeparadoresEmbaladora;
    private Boolean selagemEmbaladora;
    private Boolean marcacaoEmbaladora;
    private Boolean humidadePaletizadora;
    private Boolean dossierPaletizadora;
    private Boolean identificacaoPaletizadora;

    private int qttCaixasPaletizadora;

    public Rotulagem(DadosCadastroRotulagem data) {
        this.fkEnchimento = data.fkEnchimento();
        this.fkRespRotulagem = data.fkRespRotulagem();
        this.fkRespEmbalamento = data.fkRespEmbalamento();
        this.fkRespProducao = data.fkRespProducao();
        this.dataInicioRotulagem = data.dataInicioRotulagem();
        this.dataFimRotulagem = data.dataFimRotulagem();
        this.funcionamentoLavadoraRotulagem = data.funcionamentoLavadoraRotulagem();
        this.funcionamentoSecadoraRotulagem = data.funcionamentoSecadoraRotulagem();
        this.equipamentosOkRotulagem = data.equipamentosOkRotulagem();
        this.capsulaAcordoCapsuladora = data.capsulaAcordoCapsuladora();
        this.capsulagem = data.capsulagem();
        this.defeitosCapsuladora = data.defeitosCapsuladora();
        this.materiaisRotuladora = data.materiaisRotuladora();
        this.defeitosVisuaisRotuladora = data.defeitosVisuaisRotuladora();
        this.descricaoRotuladora = data.descricaoRotuladora();
        this.marcacaoRotuladora = data.marcacaoRotuladora();
        this.imagemRotuladora = data.imagemRotuladora();
        this.caixaAcordoEmbaladora = data.caixaAcordoEmbaladora();
        this.separadoresEmbaladora = data.separadoresEmbaladora();
        this.colocacaoSeparadoresEmbaladora = data.colocacaoSeparadoresEmbaladora();
        this.selagemEmbaladora = data.selagemEmbaladora();
        this.marcacaoEmbaladora = data.marcacaoEmbaladora();
        this.humidadePaletizadora = data.humidadePaletizadora();
        this.dossierPaletizadora = data.dossierPaletizadora();
        this.qttCaixasPaletizadora = data.qttCaixasPaletizadora();
        this.identificacaoPaletizadora = data.identificacaoPaletizadora();
    }

}
