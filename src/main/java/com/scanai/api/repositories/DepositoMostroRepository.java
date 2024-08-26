package com.scanai.api.repositories;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.depositomostro.Depositomostro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoMostroRepository extends JpaRepository<Depositomostro, Long> {
}
