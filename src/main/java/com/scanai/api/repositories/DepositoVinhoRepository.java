package com.scanai.api.repositories;

import com.scanai.api.domain.depositovinho.Depositovinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepositoVinhoRepository extends JpaRepository<Depositovinho, Long> {
    Optional<Depositovinho> findByFkvinhoAndDatafimIsNull(Long fkvinho);
}
