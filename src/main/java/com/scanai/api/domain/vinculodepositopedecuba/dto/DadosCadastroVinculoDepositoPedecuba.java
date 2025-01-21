package com.scanai.api.domain.vinculodepositopedecuba.dto;

import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroVinculoDepositoPedecuba(@NotNull Long depositoId, Long fkpedecuba, @NotNull float volume, List<DadosCadastroProdutoAdicionadoPeDeCuba.ProdutoDTO> produtos, @NotNull Long funcionarioId) {
}
