package com.scanai.api.services;

import com.scanai.api.domain.produtoadcvinho.ProdutoAdicionadovinho;
import com.scanai.api.domain.produtoadcvinho.dto.*;

import java.util.List;

public interface ProdutoAdicionadoVinhoServiceInterface {

    ProdutoAdicionadovinho register(DadosCadastroProdutoAdicionadoVinho dados);

    List<DadosDetalhamentoProdutoAdicionadoVinho> getAllByVinhoId(Long fkVinho);

    DadosDetalhamentoProdutoAdicionadoVinho update(DadosAtualizarProdutoAdicionadoVinho dados);

    void hardDelete(Long id);
}
