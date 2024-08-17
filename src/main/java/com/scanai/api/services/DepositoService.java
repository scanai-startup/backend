package com.scanai.api.services;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.RegisterDepositoDTO;
import com.scanai.api.repositories.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositoService {

    @Autowired
    DepositoRepository repository;

    public Deposito createDeposito(RegisterDepositoDTO data){
        if(repository.findByNumerodeposito(data.numerodeposito()) != null){
            return null;
        }
        Deposito newDeposito = new Deposito(data.tipodeposito(), data.numerodeposito());
        return newDeposito;
    }

    public void invalidadeDeposito(Deposito deposito) {
        deposito.setValid(false);
    }

    public void validadeDeposito(Deposito deposito) {
        deposito.setValid(true);
    }
}
