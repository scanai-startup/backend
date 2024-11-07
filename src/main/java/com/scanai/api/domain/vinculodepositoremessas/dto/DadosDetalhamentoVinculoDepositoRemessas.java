package com.scanai.api.domain.vinculodepositoremessas.dto;

public record DadosDetalhamentoVinculoDepositoRemessas(Long depositoId,Long mostroId, Long depositoMostroId, Long funcionarioId, java.util.List<Long> remessaUvaIdList) {
}
