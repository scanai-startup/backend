package com.scanai.api.repositories;

import com.scanai.api.domain.produtoadcvinho.ProdutoAdicionadovinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosDetalhamentoProdutoAdicionadoVinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoAdicionadovinhoRepository extends JpaRepository<ProdutoAdicionadovinho, Long> {
    List<DadosDetalhamentoProdutoAdicionadoVinho> findAllByFkvinho(Long fkVinho);
}
