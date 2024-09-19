package com.scanai.api.services;

import com.scanai.api.domain.depositomostro.Depositomostro;
import com.scanai.api.domain.depositomostro.dto.RegisterDepositomostroDTO;
import org.springframework.stereotype.Service;

@Service
public class DepositoMostroService {

    public Depositomostro createDepositomostro(RegisterDepositomostroDTO data) {
        var newDepositomostro = new Depositomostro(data.fkmostro(), data.fkdeposito(), data.datainicio(), data.fkfuncionario());
        return newDepositomostro;
    }
}