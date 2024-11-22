package com.scanai.api.domain.produtoadcpedecuba.dto;

import com.scanai.api.domain.produtoadcpedecuba.UnidadeDeMedida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroProdutoAdicionadoPeDeCuba(@NotNull Long fkpedecuba, @NotNull List<ProdutoDTO> produtos
) {
    public static record ProdutoDTO(
            @NotNull String nome,
            @NotNull int quantidade,
            @NotNull UnidadeDeMedida unidadeDeMedida
    ) {}
}
