package com.scanai.api.services;

import com.scanai.api.domain.depositovinho.Depositovinho;
import com.scanai.api.domain.depositovinho.dto.RegisterDepositovinhoDTO;
import org.springframework.stereotype.Service;

@Service
public class DepositoVinhoService {

    public Depositovinho createDepositovinho(RegisterDepositovinhoDTO data) {
        var newDepositovinho = new Depositovinho(data.fkfuncionario(), data.datainicio(), data.fkdeposito(), data.fkvinho() );
        return newDepositovinho;
    }
}
