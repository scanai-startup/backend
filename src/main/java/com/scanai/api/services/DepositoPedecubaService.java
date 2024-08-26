package com.scanai.api.services;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.RegisterDepositopedecubaDTO;
import org.springframework.stereotype.Service;

@Service
public class DepositoPedecubaService {

    public Depositopedecuba createDepositopedecuba(RegisterDepositopedecubaDTO data) {
        var newDepositopedecuba = new Depositopedecuba(data.fkpedecuba(), data.fkdeposito(), data.datainicio(), data.fkfuncionario());
        return newDepositopedecuba;
    }
}
