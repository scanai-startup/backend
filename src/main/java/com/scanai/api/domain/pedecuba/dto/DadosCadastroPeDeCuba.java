package com.scanai.api.domain.pedecuba.dto;

import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDate;
import java.util.List;

public record DadosCadastroPeDeCuba(@NotNull Long fkfuncionario, @NotNull LocalDate datainicio, @NotNull int volume, @Null List<DadosCadastroProdutoAdicionadoPeDeCuba.ProdutoDTO> produtos
) {
    public static record ProdutoDTO(
            @Null String nome,
            @Null int quantidade
    ) {}
}