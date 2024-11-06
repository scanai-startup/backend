package com.scanai.api.services;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosCadastroDepositoPeDeCuba;
import com.scanai.api.repositories.DepositoPedecubaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositoPedecubaService {

    @Autowired
    DepositoPedecubaRepository repository;

    public Depositopedecuba register(DadosCadastroDepositoPeDeCuba data) {
        var newDepositopedecuba = new Depositopedecuba(data);
        repository.save(newDepositopedecuba);
        return newDepositopedecuba;
    }
}
