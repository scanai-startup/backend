package com.scanai.api.services;

import com.scanai.api.domain.depositovinho.Depositovinho;
import com.scanai.api.domain.depositovinho.dto.DadosCadastroDepositoVinho;
import com.scanai.api.repositories.DepositoRepository;
import com.scanai.api.repositories.DepositoVinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DepositoVinhoService {

    @Autowired
    DepositoVinhoRepository depositoVinhoRepository;

    @Autowired
    DepositoRepository depositoRepository;

    public Depositovinho register(DadosCadastroDepositoVinho data) {
        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()) || depositoRepository.existsPeDeCubaAtivo(data.fkdeposito()) || depositoRepository.existsMostroAtivo(data.fkdeposito())){
            throw new DataIntegrityViolationException("Impossível inserir, o deposito já contém outro produto ativo");
        }
        var newDepositovinho = new Depositovinho(data.fkfuncionario(), data.datainicio(), data.fkdeposito(), data.fkvinho() );
        depositoVinhoRepository.save(newDepositovinho);
        return newDepositovinho;
    }
}
