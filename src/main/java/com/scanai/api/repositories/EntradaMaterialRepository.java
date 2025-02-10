package com.scanai.api.repositories;

import com.scanai.api.domain.entradamaterial.EntradaMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntradaMaterialRepository extends JpaRepository<EntradaMaterial, Long> {
    List<EntradaMaterial> findAllByFkmaterial(Long id);
}
