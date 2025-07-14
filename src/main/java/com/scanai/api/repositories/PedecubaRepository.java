package com.scanai.api.repositories;

import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosDetalhamentoPeDeCuba;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedecubaRepository extends JpaRepository<Pedecuba, Long> {
    public List<DadosDetalhamentoPeDeCuba> findAllByValidTrue();
}
