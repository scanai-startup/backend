package com.scanai.api.domain.pedecuba.dto;

import com.scanai.api.domain.produtoadcpedecuba.UnidadeDeMedida;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDate;
import java.util.List;

public record DadosCadastroPeDeCuba(@NotNull Long fkfuncionario, Long fkpedecuba, @NotNull LocalDate datainicio, @NotNull float volume, List<DadosCadastroProdutoAdicionadoPeDeCuba.ProdutoDTO> produtos
) {
    public static record ProdutoDTO(
            String nome,
            int quantidade,
            UnidadeDeMedida unidadeDeMedida
    ) {}
}