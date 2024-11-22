package com.scanai.api.services;

import com.scanai.api.domain.depositopedecuba.dto.DadosCadastroDepositoPeDeCuba;
import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import com.scanai.api.repositories.PedecubaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PedecubaService {

    @Autowired
    PedecubaRepository repository;

    @Autowired
    ProdutoAdicionadopedecubaService produtoAdicionadopedecubaService;

    @Autowired
    DepositoPedecubaService depositoPedecubaService;

    public Pedecuba register(DadosCadastroPeDeCuba dados) {
        Pedecuba newPedecuba = new Pedecuba(dados);
        repository.save(newPedecuba);
        if(dados.produtos() != null){
            produtoAdicionadopedecubaService.register(new DadosCadastroProdutoAdicionadoPeDeCuba(newPedecuba.getId(), dados.produtos()));
        }
        depositoPedecubaService.register(new DadosCadastroDepositoPeDeCuba(newPedecuba.getId(), dados.fkdeposito(), LocalDate.now(), dados.fkfuncionario()));
        return newPedecuba;
    }

    public void softDelete(Pedecuba pedecuba) {
        pedecuba.setValid(false);
    }

    public void activate(Pedecuba pedecuba) {
        pedecuba.setValid(true);
    }
}
