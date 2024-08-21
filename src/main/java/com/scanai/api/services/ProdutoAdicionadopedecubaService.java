package com.scanai.api.services;

import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.RegisterPedecubaDTO;
import com.scanai.api.domain.produtoadcpedecuba.ProdutoAdicionadopedecuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.RegisterProdutoadcpedecubaDTO;
import org.springframework.stereotype.Service;

@Service
public class ProdutoAdicionadopedecubaService {
    public ProdutoAdicionadopedecuba createProdutoadcpedecuba(RegisterProdutoadcpedecubaDTO data) {
        return new ProdutoAdicionadopedecuba(data.fkpedecuba(), data.nome(), data.quantidade());
    }
}
