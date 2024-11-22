package com.scanai.api.domain.vinculodepositoremessas.dto;

import java.util.List;

public record DadosCadastroVinculoDepositoRemessas(List<Long> remessaUvaIdList, Long depositoId, Long mostroId, Long funcionarioId) {
}
