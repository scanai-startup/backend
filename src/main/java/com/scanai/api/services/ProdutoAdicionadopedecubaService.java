package com.scanai.api.services;

import com.scanai.api.domain.produtoadcpedecuba.ProdutoAdicionadopedecuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import com.scanai.api.repositories.ProdutoAdicionadopedecubaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoAdicionadopedecubaService {

    @Autowired
    ProdutoAdicionadopedecubaRepository repository;

    public ProdutoAdicionadopedecuba register(DadosCadastroProdutoAdicionadoPeDeCuba dados) {
        ProdutoAdicionadopedecuba newProdutoadcpedecuba = new ProdutoAdicionadopedecuba(dados);
        repository.save(newProdutoadcpedecuba);
        return newProdutoadcpedecuba;
    }
}
