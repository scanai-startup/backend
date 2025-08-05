package com.scanai.api.services.implement;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosCadastroAnalisePeDeCuba;
import com.scanai.api.repositories.AnalisepedecubaRepository;
import com.scanai.api.services.AnalisePeDeCubaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalisePeDeCubaService implements AnalisePeDeCubaServiceInterface {

    @Autowired
    AnalisepedecubaRepository analisePeDeCubaRepository;

    public Analisepedecuba register(DadosCadastroAnalisePeDeCuba data) {
        var newAnalisepedecuba = new Analisepedecuba(data);
        analisePeDeCubaRepository.save(newAnalisepedecuba);
        return newAnalisepedecuba;
    }
}
