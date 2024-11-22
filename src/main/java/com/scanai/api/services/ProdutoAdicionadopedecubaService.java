package com.scanai.api.services;


import com.scanai.api.domain.produtoadcpedecuba.ProdutoAdicionadopedecuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosAtualizarProdutoAdicionadoPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosDetalhamentoProdutoAdicionadoPeDeCuba;
import com.scanai.api.repositories.ProdutoAdicionadopedecubaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoAdicionadopedecubaService {

    @Autowired
    ProdutoAdicionadopedecubaRepository repository;

    public List<ProdutoAdicionadopedecuba> register(DadosCadastroProdutoAdicionadoPeDeCuba dados) {
        List<ProdutoAdicionadopedecuba> produtosSalvos = dados.produtos().stream()
                .map(produtoDTO -> new ProdutoAdicionadopedecuba(
                        dados.fkpedecuba(),
                        produtoDTO.nome(),
                        produtoDTO.quantidade(),
                        produtoDTO.unidadeDeMedida()
                ))
                .peek(repository::save)
                .toList();

        return produtosSalvos;
    }

    public List<DadosDetalhamentoProdutoAdicionadoPeDeCuba> getAllByPeDeCubaId(Long fkPeDeCuba) {
        List<DadosDetalhamentoProdutoAdicionadoPeDeCuba> lista = repository.findAllByFkpedecuba(fkPeDeCuba);
        return lista;
    }

    public DadosDetalhamentoProdutoAdicionadoPeDeCuba update(DadosAtualizarProdutoAdicionadoPeDeCuba dados) {
        Optional<ProdutoAdicionadopedecuba> produtoAdicionadoPeDeCuba = repository.findById(dados.id());
        if(produtoAdicionadoPeDeCuba.isEmpty()){
            throw new EntityNotFoundException("No produto adicionado with this id");
        }
        produtoAdicionadoPeDeCuba.get().atualizar(dados);
        return new DadosDetalhamentoProdutoAdicionadoPeDeCuba(produtoAdicionadoPeDeCuba.get());
    }

    public void hardDelete(Long id) {
        repository.deleteById(id);
    }
}
