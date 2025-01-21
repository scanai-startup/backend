package com.scanai.api.domain.vinculodepositoremessas.dto;

public record DadosDetalhamentoVinculoDepositoRemessas(Long depositoId,Long mostroId, Float volume, Long funcionarioId, java.util.List<Long> remessaUvaIdList, String message) {
}
