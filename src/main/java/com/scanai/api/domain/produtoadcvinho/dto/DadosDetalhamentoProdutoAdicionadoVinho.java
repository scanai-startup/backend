package com.scanai.api.domain.produtoadcvinho.dto;

import com.scanai.api.domain.produtoadcvinho.ProdutoAdicionadovinho;


public record DadosDetalhamentoProdutoAdicionadoVinho(Long id, Long fkvinho, String nome, int quantidade) {
    public DadosDetalhamentoProdutoAdicionadoVinho(ProdutoAdicionadovinho produtoAdicionadovinho) {
        this(
                produtoAdicionadovinho.getId(),
                produtoAdicionadovinho.getFkvinho(),
                produtoAdicionadovinho.getNome(),
                produtoAdicionadovinho.getQuantidade()
        );
    }
}
