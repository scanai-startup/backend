package com.scanai.api.domain.produtoadcpedecuba.dto;

import com.scanai.api.domain.produtoadcpedecuba.ProdutoAdicionadopedecuba;


public record DadosDetalhamentoProdutoAdicionadoPeDeCuba(Long id, Long fkpedecuba, String nome, int quantidade) {
    public DadosDetalhamentoProdutoAdicionadoPeDeCuba(ProdutoAdicionadopedecuba produtoAdicionadopedecuba) {
        this(
                produtoAdicionadopedecuba.getId(),
                produtoAdicionadopedecuba.getFkpedecuba(),
                produtoAdicionadopedecuba.getNome(),
                produtoAdicionadopedecuba.getQuantidade()
        );
    }
}
