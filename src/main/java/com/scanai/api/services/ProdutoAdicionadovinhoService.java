package com.scanai.api.services;

import com.scanai.api.domain.produtoadcvinho.ProdutoAdicionadovinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosAtualizarProdutoAdicionadoVinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosCadastroProdutoAdicionadoVinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosDetalhamentoProdutoAdicionadoVinho;
import com.scanai.api.repositories.ProdutoAdicionadovinhoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoAdicionadovinhoService {

    @Autowired
    ProdutoAdicionadovinhoRepository repository;

    public ProdutoAdicionadovinho register(DadosCadastroProdutoAdicionadoVinho dados) {
        ProdutoAdicionadovinho newProdutoadcvinho = new ProdutoAdicionadovinho(dados);
        repository.save(newProdutoadcvinho);
        return newProdutoadcvinho;
    }

    public List<DadosDetalhamentoProdutoAdicionadoVinho> getAllByVinhoId(Long fkVinho) {
        List<DadosDetalhamentoProdutoAdicionadoVinho> lista = repository.findAllByFkvinho(fkVinho);
        return lista;
    }

    public DadosDetalhamentoProdutoAdicionadoVinho update(DadosAtualizarProdutoAdicionadoVinho dados) {
        Optional<ProdutoAdicionadovinho> produtoAdicionadoVinho = repository.findById(dados.id());
        if(produtoAdicionadoVinho.isEmpty()){
            throw new EntityNotFoundException("No produto adicionado with this id");
        }
        produtoAdicionadoVinho.get().atualizar(dados);
        return new DadosDetalhamentoProdutoAdicionadoVinho(produtoAdicionadoVinho.get());
    }

    public void hardDelete(Long id) {
        repository.deleteById(id);
    }
}
