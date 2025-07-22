package com.scanai.api.services;

import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;
import com.scanai.api.domain.pedecuba.dto.DadosDetalhamentoPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import com.scanai.api.repositories.PedecubaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public void softDelete(Long id) {
        Pedecuba peDeCuba = repository.getReferenceById(id);
        peDeCuba.setValid(false);
    }

    public void activate(Long id) {
        Pedecuba peDeCuba = repository.getReferenceById(id);
        peDeCuba.setValid(true);
    }

    public Pedecuba getElement(Long id) {
        return repository.getReferenceById(id);
    }

    public List<DadosDetalhamentoPeDeCuba> getAll(){
        return repository.findAllByValidTrue();
    }
}
