package com.scanai.api.repositories;

import com.scanai.api.domain.pedecuba.Pedecuba;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedecubaRepository extends JpaRepository<Pedecuba, Long> {
    public List<Pedecuba> findAllByValidTrue();
}
