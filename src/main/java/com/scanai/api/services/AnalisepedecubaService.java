package com.scanai.api.services;

import com.scanai.api.domain.analisespedecuba.Analisespedecuba;
import com.scanai.api.domain.analisespedecuba.dto.RegisterAnalisepedecubaDTO;
import org.springframework.stereotype.Service;

@Service
public class AnalisepedecubaService {

    public Analisespedecuba createAnalisespedecuba(RegisterAnalisepedecubaDTO data) {
        var newAnalisespedecuba = new Analisespedecuba(data.fkpedecuba(), data.fkfuncionario(), data.densidade(), data.data(), data.temperatura());
        return newAnalisespedecuba;
    }
}
