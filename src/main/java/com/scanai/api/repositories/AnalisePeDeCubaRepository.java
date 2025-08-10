package com.scanai.api.repositories;

import com.scanai.api.domain.analisepedecuba.AnalisePeDeCubaService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalisePeDeCubaRepository extends JpaRepository<AnalisePeDeCubaService, Long> {
    public List<AnalisePeDeCubaService> findAllByFkpedecuba(Long fkpedecuba);
}
