package com.scanai.api.services.implement;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosCadastroAnalisePeDeCuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosDetalhamentoAnalisePeDeCuba;
import com.scanai.api.repositories.AnalisePeDeCubaRepository;
import com.scanai.api.services.AnalisePeDeCubaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalisePeDeCubaService implements AnalisePeDeCubaServiceInterface {

    @Autowired
    AnalisePeDeCubaRepository analisePeDeCubaRepository;

    public DadosDetalhamentoAnalisePeDeCuba register(DadosCadastroAnalisePeDeCuba data) {
        return new DadosDetalhamentoAnalisePeDeCuba(analisePeDeCubaRepository.save(new Analisepedecuba(data)));
    }
}
