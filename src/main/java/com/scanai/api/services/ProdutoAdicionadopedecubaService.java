package com.scanai.api.services;

import com.scanai.api.domain.produtoadcpedecuba.ProdutoAdicionadopedecuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosDetalhamentoProdutoAdicionadoPeDeCuba;
import com.scanai.api.repositories.ProdutoAdicionadopedecubaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
