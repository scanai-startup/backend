package com.scanai.api.repositories;

import com.scanai.api.domain.lotematerial.Lotematerial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotematerialRepository extends JpaRepository<Lotematerial, Long> {
    public List<Lotematerial> findAllByFkmaterial(Long fkmaterial);
}
