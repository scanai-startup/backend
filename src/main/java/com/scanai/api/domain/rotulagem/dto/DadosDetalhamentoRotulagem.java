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
                rotulagem.getFkEnchimento(),
                rotulagem.getFkRespRotulagem(),
                rotulagem.getFkRespEmbalamento(),
                rotulagem.getFkRespProducao(),
                rotulagem.getDataInicioRotulagem(),
                rotulagem.getDataFimRotulagem(),
                rotulagem.getFuncionamentoLavadoraRotulagem(),
                rotulagem.getFuncionamentoSecadoraRotulagem(),
                rotulagem.getEquipamentosOkRotulagem(),
                rotulagem.getCapsulaAcordoCapsuladora(),
                rotulagem.getCapsulagem(),
                rotulagem.getDefeitosCapsuladora(),
                rotulagem.getMateriaisRotuladora(),
                rotulagem.getDefeitosVisuaisRotuladora(),
                rotulagem.getDescricaoRotuladora(),
                rotulagem.getMarcacaoRotuladora(),
                rotulagem.getImagemRotuladora(),
                rotulagem.getCaixaAcordoEmbaladora(),
                rotulagem.getSeparadoresEmbaladora(),
                rotulagem.getColocacaoSeparadoresEmbaladora(),
                rotulagem.getSelagemEmbaladora(),
                rotulagem.getMarcacaoEmbaladora(),
                rotulagem.getHumidadePaletizadora(),
                rotulagem.getDossierPaletizadora(),
                rotulagem.getIdentificacaoPaletizadora(),
                rotulagem.getQttCaixasPaletizadora()
        );
    }
}
