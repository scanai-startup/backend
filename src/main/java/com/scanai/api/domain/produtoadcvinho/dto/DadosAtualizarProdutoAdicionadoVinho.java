package com.scanai.api.domain.produtoadcvinho.dto;

import com.scanai.api.domain.produtoadcpedecuba.UnidadeDeMedida;

public record DadosAtualizarProdutoAdicionadoVinho(
        Long id,
        Long fkvinho,
        String nome,
        int quantidade,
        UnidadeDeMedida unidade ) {

}
