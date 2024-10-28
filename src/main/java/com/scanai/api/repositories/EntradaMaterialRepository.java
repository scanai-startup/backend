package com.scanai.api.repositories;

import com.scanai.api.domain.entradamaterial.EntradaMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaMaterialRepository extends JpaRepository<EntradaMaterial, Long> {
}
