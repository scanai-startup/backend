package com.scanai.api.domain.rotulagem.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroRotulagem(@NotNull Long fkEnchimento,
                                     @NotNull Long fkRespRotulagem,
                                     @NotNull Long fkRespEmbalamento,
                                     @NotNull Long fkRespProducao,

                                     @NotNull Date dataInicioRotulagem,
                                     Date dataFimRotulagem,// Pode ser nulo, caso a rotulagem ainda esteja em andamento

                                     @NotNull Boolean funcionamentoLavadoraRotulagem,
                                     @NotNull Boolean funcionamentoSecadoraRotulagem,
                                     @NotNull Boolean equipamentosOkRotulagem,
                                     @NotNull Boolean capsulaAcordoCapsuladora,
                                     @NotNull Boolean capsulagem,

                                     Boolean defeitosCapsuladora, // Pode ser nulo se não houver defeitos
                                     Boolean materiaisRotuladora,
                                     Boolean defeitosVisuaisRotuladora,
                                     Boolean descricaoRotuladora,
                                     Boolean marcacaoRotuladora,
                                     Boolean imagemRotuladora,

                                     @NotNull Boolean caixaAcordoEmbaladora,
                                     @NotNull Boolean separadoresEmbaladora,
                                     @NotNull Boolean colocacaoSeparadoresEmbaladora,
                                     @NotNull Boolean selagemEmbaladora,
                                     @NotNull Boolean marcacaoEmbaladora,

                                     Boolean humidadePaletizadora,
                                     Boolean dossierPaletizadora,

                                     @NotNull int qttCaixasPaletizadora,

                                     Boolean identificacaoPaletizadora) {
}
