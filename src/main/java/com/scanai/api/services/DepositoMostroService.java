package com.scanai.api.services;

import com.scanai.api.domain.depositomostro.Depositomostro;
import com.scanai.api.domain.depositomostro.dto.DadosCadastroDepositoMostro;
import com.scanai.api.repositories.DepositoMostroRepository;
import com.scanai.api.repositories.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DepositoMostroService {

    @Autowired
    DepositoMostroRepository depositoMostroRepository;

    @Autowired
    DepositoRepository depositoRepository;

    public Depositomostro register(DadosCadastroDepositoMostro data) {
        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()) || depositoRepository.existsPeDeCubaAtivo(data.fkdeposito())){
            throw new DataIntegrityViolationException("Impossível inserir, o deposito já contém outro produto ativo");
        }
        var newDepositomostro = new Depositomostro(data.fkmostro(), data.fkdeposito(), data.datainicio(), data.fkfuncionario());
        depositoMostroRepository.save(newDepositomostro);
        return newDepositomostro;
    }
}
