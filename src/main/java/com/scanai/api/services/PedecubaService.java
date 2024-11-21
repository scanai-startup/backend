package com.scanai.api.services;

import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import com.scanai.api.repositories.PedecubaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedecubaService {

    @Autowired
    PedecubaRepository repository;

    @Autowired
    ProdutoAdicionadopedecubaService produtoAdicionadopedecubaService;

    public Pedecuba register(DadosCadastroPeDeCuba dados) {
        Pedecuba newPedecuba = new Pedecuba(dados);
        repository.save(newPedecuba);
        if(dados.produtos() != null){
            produtoAdicionadopedecubaService.register(new DadosCadastroProdutoAdicionadoPeDeCuba(newPedecuba.getId(), dados.produtos()));
        }
        return newPedecuba;
    }

    public void softDelete(Pedecuba pedecuba) {
        pedecuba.setValid(false);
    }

    public void activate(Pedecuba pedecuba) {
        pedecuba.setValid(true);
    }
}
