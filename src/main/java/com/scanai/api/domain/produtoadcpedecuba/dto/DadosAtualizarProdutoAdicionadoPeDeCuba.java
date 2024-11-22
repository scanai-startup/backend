package com.scanai.api.domain.produtoadcpedecuba.dto;

import com.scanai.api.domain.produtoadcpedecuba.UnidadeDeMedida;

public record DadosAtualizarProdutoAdicionadoPeDeCuba(
        Long id,
        Long fkpedecuba,
        String nome,
        int quantidade,
        UnidadeDeMedida unidade ) {

}
