package com.scanai.api.services;

import com.scanai.api.domain.produtoadcvinho.ProdutoAdicionadovinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosCadastroProdutoAdicionadoVinho;
import com.scanai.api.repositories.ProdutoAdicionadovinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoAdicionadovinhoService {

    @Autowired
    ProdutoAdicionadovinhoRepository repository;

    public ProdutoAdicionadovinho register(DadosCadastroProdutoAdicionadoVinho dados) {
        ProdutoAdicionadovinho newProdutoadcvinho = new ProdutoAdicionadovinho(dados);
        repository.save(newProdutoadcvinho);
        return newProdutoadcvinho;
    }
}
