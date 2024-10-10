package com.scanai.api.services;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosCadastroAnalisePeDeCuba;
import org.springframework.stereotype.Service;

@Service
public class AnalisepedecubaService {

    public Analisepedecuba register(DadosCadastroAnalisePeDeCuba data) {
        var newAnalisespedecuba = new Analisepedecuba(data.fkpedecuba(), data.fkfuncionario(), data.densidade(), data.data(), data.temperatura());
        return newAnalisespedecuba;
    }
}
