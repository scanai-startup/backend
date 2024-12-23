package com.scanai.api.repositories;

import com.scanai.api.domain.depositomostro.DepositoMostro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoMostroRepository extends JpaRepository<DepositoMostro, Long> {
    DepositoMostro findByFkdepositoAndFkmostro(Long fkdeposito, Long fkmostro);
}
