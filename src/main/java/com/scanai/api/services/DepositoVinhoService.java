package com.scanai.api.services;

import com.scanai.api.domain.depositovinho.Depositovinho;
import com.scanai.api.domain.depositovinho.dto.DadosCadastroDepositoVinho;
import com.scanai.api.repositories.DepositoVinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositoVinhoService {

    @Autowired
    DepositoVinhoRepository repository;

    public Depositovinho register(DadosCadastroDepositoVinho data) {
        var newDepositovinho = new Depositovinho(data.fkfuncionario(), data.datainicio(), data.fkdeposito(), data.fkvinho() );
        repository.save(newDepositovinho);
        return newDepositovinho;
    }
}
