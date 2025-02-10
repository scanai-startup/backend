package com.scanai.api.domain.vinculodepositoremessas.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroVinculoDepositoRemessas(@NotNull List<Long> remessaUvaIdList, @NotNull Float volume,@NotNull Long depositoId,@NotNull Long funcionarioId) {
}
