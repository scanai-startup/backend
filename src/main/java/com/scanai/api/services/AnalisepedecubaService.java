package com.scanai.api.services;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosCadastroAnalisePeDeCuba;
import com.scanai.api.repositories.AnalisepedecubaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalisepedecubaService {

    @Autowired
    AnalisepedecubaRepository repository;

    public Analisepedecuba register(DadosCadastroAnalisePeDeCuba data) {
        var newAnalisepedecuba = new Analisepedecuba(data.fkpedecuba(), data.fkfuncionario(), data.densidade(), data.data(), data.temperatura());
        repository.save(newAnalisepedecuba);
        return newAnalisepedecuba;
    }
}
