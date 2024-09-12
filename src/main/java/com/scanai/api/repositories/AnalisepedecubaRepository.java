package com.scanai.api.repositories;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalisepedecubaRepository extends JpaRepository<Analisepedecuba, Long> {
    public List<Analisepedecuba> findAllByFkpedecuba(Long fkpedecuba);
}
