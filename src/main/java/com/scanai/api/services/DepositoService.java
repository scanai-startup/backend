package com.scanai.api.services;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.DadosCadastroDeposito;
import com.scanai.api.domain.deposito.dto.DadosAtualizarDeposito;
import com.scanai.api.domain.deposito.dto.DadosInformacoesDepositos;
import com.scanai.api.repositories.DepositoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository repository;

    public Deposito register(DadosCadastroDeposito data){
        Deposito newDeposito = new Deposito(data);
        repository.save(newDeposito);
        return newDeposito;
    }

    public Deposito update(DadosAtualizarDeposito data){
        Deposito deposito = repository.findByNumerodeposito(data.numeroAtual());
        if(deposito == null){
            throw new EntityNotFoundException("Deposito: " + data.numeroAtual() + " Não Encontrado");
        }
        deposito.setNumerodeposito(data.numeroNovo());
        return deposito;
    }

    public void softDelete(Deposito deposito) {
        deposito.setValid(false);
    }

    public void activate(Deposito deposito) {
        deposito.setValid(true);
    }

    public List<DadosInformacoesDepositos> getAllDepositosWithInformations(){
        return repository.getAllDepositosWithInformations();
    }


}
