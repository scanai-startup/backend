package com.scanai.api.services;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.DadosCadastroDeposito;
import com.scanai.api.domain.deposito.dto.DadosAtualizarDeposito;
import com.scanai.api.repositories.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository repository;

    public Deposito createDeposito(DadosCadastroDeposito data){
        if(repository.findByNumerodeposito(data.numerodeposito()) != null){
            return null;
        }
        Deposito newDeposito = new Deposito(data.tipodeposito(), data.numerodeposito());
        return newDeposito;
    }

    public Boolean updateDeposito(DadosAtualizarDeposito data){
        Deposito deposito = repository.findByNumerodeposito(data.numeroNovo());
        if(deposito != null){
            return false;
        }
        Deposito deposito1 = repository.findByNumerodeposito(data.numeroAtual());
        deposito1.setNumerodeposito(data.numeroNovo());
        return true;
    }

    public void invalidadeDeposito(Deposito deposito) {
        deposito.setValid(false);
    }

    public void validadeDeposito(Deposito deposito) {
        deposito.setValid(true);
    }
}
