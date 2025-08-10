package com.scanai.api.domain.produtoadcvinho.dto;

import com.scanai.api.domain.produtoadcpedecuba.UnidadeDeMedida;
import com.scanai.api.domain.produtoadcvinho.ProdutoAdicionadovinho;

public record DadosAtualizarProdutoAdicionadoVinho(
        Long id,
        Long fkvinho,
        String nome,
        int quantidade,
        UnidadeDeMedida unidade ) {


}
