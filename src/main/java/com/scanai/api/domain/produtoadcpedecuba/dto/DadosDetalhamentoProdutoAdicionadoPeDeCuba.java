package com.scanai.api.domain.produtoadcpedecuba.dto;

import com.scanai.api.domain.produtoadcpedecuba.ProdutoAdicionadopedecuba;
import com.scanai.api.domain.produtoadcpedecuba.UnidadeDeMedida;

import java.time.LocalDateTime;


public record DadosDetalhamentoProdutoAdicionadoPeDeCuba(Long id, Long fkpedecuba, String nome, int quantidade, UnidadeDeMedida unidade, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public DadosDetalhamentoProdutoAdicionadoPeDeCuba(ProdutoAdicionadopedecuba produtoAdicionadopedecuba) {
        this(
                produtoAdicionadopedecuba.getId(),
                produtoAdicionadopedecuba.getFkpedecuba(),
                produtoAdicionadopedecuba.getNome(),
                produtoAdicionadopedecuba.getQuantidade(),
                produtoAdicionadopedecuba.getUnidade(),
                produtoAdicionadopedecuba.getCreatedAt(),
                produtoAdicionadopedecuba.getUpdatedAt()
        );
    }
}
