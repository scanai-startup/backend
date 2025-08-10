package com.scanai.api.repositories;

import com.scanai.api.domain.produtoadcpedecuba.ProdutoAdicionadopedecuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosDetalhamentoProdutoAdicionadoPeDeCuba;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoAdicionadoPeDeCubaRepository extends JpaRepository<ProdutoAdicionadopedecuba, Long> {

    List<DadosDetalhamentoProdutoAdicionadoPeDeCuba> findAllByFkpedecuba(Long Fkpedecuba);
}
