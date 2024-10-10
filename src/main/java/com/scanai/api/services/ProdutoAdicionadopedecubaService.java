package com.scanai.api.services;

import com.scanai.api.domain.produtoadcpedecuba.ProdutoAdicionadopedecuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import org.springframework.stereotype.Service;

@Service
public class ProdutoAdicionadopedecubaService {
    public ProdutoAdicionadopedecuba createProdutoadcpedecuba(DadosCadastroProdutoAdicionadoPeDeCuba data) {
        return new ProdutoAdicionadopedecuba(data.fkpedecuba(), data.nome(), data.quantidade());
    }
}
