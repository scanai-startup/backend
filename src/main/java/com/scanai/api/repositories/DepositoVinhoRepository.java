package com.scanai.api.repositories;

import com.scanai.api.domain.depositovinho.Depositovinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoVinhoRepository extends JpaRepository<Depositovinho, Long> {
    public Depositovinho findByFkvinhoAndDatafimIsNull(Long fkvinho);
}
