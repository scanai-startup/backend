package com.scanai.api.services.implement;


import com.scanai.api.domain.produtoadcpedecuba.ProdutoAdicionadopedecuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosAtualizarProdutoAdicionadoPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosDetalhamentoProdutoAdicionadoPeDeCuba;
import com.scanai.api.repositories.ProdutoAdicionadopedecubaRepository;
import com.scanai.api.services.ProdutoAdicionadoPeDeCubaServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoAdicionadoPeDeCubaService implements ProdutoAdicionadoPeDeCubaServiceInterface {

    @Autowired
    ProdutoAdicionadopedecubaRepository produtoAdicionadoPeDeCubaRepository;

    public List<ProdutoAdicionadopedecuba> register(DadosCadastroProdutoAdicionadoPeDeCuba dados) {
        return dados.produtos().stream()
                .map(produtoDTO -> new ProdutoAdicionadopedecuba(
                        dados.fkpedecuba(),
                        produtoDTO.nome(),
                        produtoDTO.quantidade(),
                        produtoDTO.unidadeDeMedida()
                ))
                .peek(produtoAdicionadoPeDeCubaRepository::save)
                .toList();
    }

    public List<DadosDetalhamentoProdutoAdicionadoPeDeCuba> getAllByPeDeCubaId(Long fkPeDeCuba) {
        return produtoAdicionadoPeDeCubaRepository.findAllByFkpedecuba(fkPeDeCuba);
    }

    public DadosDetalhamentoProdutoAdicionadoPeDeCuba update(DadosAtualizarProdutoAdicionadoPeDeCuba dados) {
        Optional<ProdutoAdicionadopedecuba> produtoAdicionadoPeDeCuba = produtoAdicionadoPeDeCubaRepository.findById(dados.id());

        if(produtoAdicionadoPeDeCuba.isEmpty()){
            throw new EntityNotFoundException("No produto adicionado with this id");
        }

        produtoAdicionadoPeDeCuba.get().atualizar(dados);
        return new DadosDetalhamentoProdutoAdicionadoPeDeCuba(produtoAdicionadoPeDeCuba.get());
    }

    public void hardDelete(Long id) {
        produtoAdicionadoPeDeCubaRepository.deleteById(id);
    }
}
