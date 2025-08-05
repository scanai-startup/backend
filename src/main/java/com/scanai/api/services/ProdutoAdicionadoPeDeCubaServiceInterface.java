package com.scanai.api.services;

import com.scanai.api.domain.produtoadcpedecuba.ProdutoAdicionadopedecuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.*;

import java.util.List;

public interface ProdutoAdicionadoPeDeCubaServiceInterface {

    List<ProdutoAdicionadopedecuba> register(DadosCadastroProdutoAdicionadoPeDeCuba dados);

    List<DadosDetalhamentoProdutoAdicionadoPeDeCuba> getAllByPeDeCubaId(Long fkPeDeCuba);

    DadosDetalhamentoProdutoAdicionadoPeDeCuba update(DadosAtualizarProdutoAdicionadoPeDeCuba dados);

    void hardDelete(Long id);
}
