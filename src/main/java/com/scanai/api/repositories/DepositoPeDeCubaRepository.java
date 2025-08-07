package com.scanai.api.repositories;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepositoPeDeCubaRepository extends JpaRepository<Depositopedecuba, Long> {
    Optional<Depositopedecuba> findByFkpedecubaAndDatafimIsNull(Long fkpedecuba);
}
