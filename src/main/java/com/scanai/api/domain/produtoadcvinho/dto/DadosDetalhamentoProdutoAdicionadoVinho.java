package com.scanai.api.domain.produtoadcvinho.dto;

import com.scanai.api.domain.produtoadcpedecuba.UnidadeDeMedida;
import com.scanai.api.domain.produtoadcvinho.ProdutoAdicionadovinho;

import java.time.LocalDateTime;


public record DadosDetalhamentoProdutoAdicionadoVinho(Long id, Long fkvinho, String nome, int quantidade, UnidadeDeMedida unidade, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public DadosDetalhamentoProdutoAdicionadoVinho(ProdutoAdicionadovinho produtoAdicionadovinho) {
        this(
                produtoAdicionadovinho.getId(),
                produtoAdicionadovinho.getFkvinho(),
                produtoAdicionadovinho.getNome(),
                produtoAdicionadovinho.getQuantidade(),
                produtoAdicionadovinho.getUnidade(),
                produtoAdicionadovinho.getCreatedAt(),
                produtoAdicionadovinho.getUpdatedAt()
        );
    }
}
