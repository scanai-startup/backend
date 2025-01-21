package com.scanai.api.domain.vinculodepositoremessas.dto;

import java.util.List;

public record DadosCadastroVinculoDepositoRemessas(List<Long> remessaUvaIdList, Float volume, Long depositoId, Long mostroId, Long funcionarioId) {
}
