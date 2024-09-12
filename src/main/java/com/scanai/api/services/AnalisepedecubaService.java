package com.scanai.api.services;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;
import com.scanai.api.domain.analisepedecuba.dto.RegisterAnalisepedecubaDTO;
import org.springframework.stereotype.Service;

@Service
public class AnalisepedecubaService {

    public Analisepedecuba createAnalisespedecuba(RegisterAnalisepedecubaDTO data) {
        var newAnalisespedecuba = new Analisepedecuba(data.fkpedecuba(), data.fkfuncionario(), data.densidade(), data.data(), data.temperatura());
        return newAnalisespedecuba;
    }
}
