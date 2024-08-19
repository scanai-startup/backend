package com.scanai.api.repositories;

import com.scanai.api.domain.analisespedecuba.Analisespedecuba;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalisepedecubaRepository extends JpaRepository<Analisespedecuba, Long> {
    public List<Analisespedecuba> findAllByFkpedecuba(Long fkpedecuba);
}
