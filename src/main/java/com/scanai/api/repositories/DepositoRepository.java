package com.scanai.api.repositories;

import com.scanai.api.domain.deposito.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {

    public Deposito findByNumerodeposito(String numerodeposito);
}
