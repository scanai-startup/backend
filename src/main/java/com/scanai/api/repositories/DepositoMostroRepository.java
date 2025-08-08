package com.scanai.api.repositories;

import com.scanai.api.domain.depositomostro.DepositoMostro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepositoMostroRepository extends JpaRepository<DepositoMostro, Long> {
    Optional<DepositoMostro> findByFkdepositoAndFkmostro(Long fkdeposito, Long fkmostro);
    Optional<DepositoMostro> findByFkmostroAndDatafimIsNull(Long fkmostro);
}
