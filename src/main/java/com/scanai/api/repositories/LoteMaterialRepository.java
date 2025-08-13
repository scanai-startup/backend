package com.scanai.api.repositories;

import com.scanai.api.domain.lotematerial.Lotematerial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoteMaterialRepository extends JpaRepository<Lotematerial, Long> {
    List<Lotematerial> findAllByFkmaterial(Long id);
}
