package com.scanai.api.domain.rotulagem.dto;

import com.scanai.api.domain.rotulagem.Rotulagem;

import java.time.LocalDate;

public record DadosDetalhamentoRotulagem(
        Long id,
        Long fkEnchimento,
        Long fkRespRotulagem,
        Long fkRespEmbalamento,
        Long fkRespProducao,
        LocalDate dataInicioRotulagem,
        LocalDate dataFimRotulagem,
        Boolean funcionamentoLavadoraRotulagem,
        Boolean funcionamentoSecadoraRotulagem,
        Boolean equipamentosOkRotulagem,
        Boolean capsulaAcordoCapsuladora,
        Boolean capsulagem,
        Boolean defeitosCapsuladora,
        Boolean materiaisRotuladora,
        Boolean defeitosVisuaisRotuladora,
        Boolean descricaoRotuladora,
        Boolean marcacaoRotuladora,
        Boolean imagemRotuladora,
        Boolean caixaAcordoEmbaladora,
        Boolean separadoresEmbaladora,
        Boolean colocacaoSeparadoresEmbaladora,
        Boolean selagemEmbaladora,
        Boolean marcacaoEmbaladora,
        Boolean humidadePaletizadora,
        Boolean dossierPaletizadora,
        Boolean identificacaoPaletizadora,
        int qttCaixasPaletizadora
) {
    public DadosDetalhamentoRotulagem(Rotulagem rotulagem) {
        this(
                rotulagem.getId(),
                rotulagem.getFkenchimento(),
                rotulagem.getFkresprotulagem(),
                rotulagem.getFkrespembalamento(),
                rotulagem.getFkrespproducao(),
                rotulagem.getDatainiciorotulagem(),
                rotulagem.getDatafimrotulagem(),
                rotulagem.getFuncionamentolavadorarotulagem(),
                rotulagem.getFuncionamentosecadorarotulagem(),
                rotulagem.getEquipamentosokrotulagem(),
                rotulagem.getCapsulaacordocapsuladora(),
                rotulagem.getCapsulagem(),
                rotulagem.getDefeitoscapsuladora(),
                rotulagem.getMateriaisrotuladora(),
                rotulagem.getDefeitosvisuaisrotuladora(),
                rotulagem.getDescricaorotuladora(),
                rotulagem.getMarcacaorotuladora(),
                rotulagem.getImagemrotuladora(),
                rotulagem.getCaixaacordoembaladora(),
                rotulagem.getSeparadoresembaladora(),
                rotulagem.getColocacaoseparadoresembaladora(),
                rotulagem.getSelagemembaladora(),
                rotulagem.getMarcacaoembaladora(),
                rotulagem.getHumidadepaletizadora(),
                rotulagem.getDossierpaletizadora(),
                rotulagem.getIdentificacaopaletizadora(),
                rotulagem.getQttcaixaspaletizadora()
        );
    }
}
