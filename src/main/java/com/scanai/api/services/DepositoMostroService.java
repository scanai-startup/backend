package com.scanai.api.services;

import com.scanai.api.domain.depositomostro.Depositomostro;
import com.scanai.api.domain.depositomostro.dto.DadosCadastroDepositoMostro;
import com.scanai.api.repositories.DepositoMostroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositoMostroService {

    @Autowired
    DepositoMostroRepository repository;

    public Depositomostro register(DadosCadastroDepositoMostro data) {
        var newDepositomostro = new Depositomostro(data);
        repository.save(newDepositomostro);
        return newDepositomostro;
    }
}
