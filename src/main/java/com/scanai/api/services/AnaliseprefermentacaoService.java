package com.scanai.api.services;

import com.scanai.api.domain.analiseprefermentacao.Analiseprefermentacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosCadastroAnalisePreFermetacao;
import com.scanai.api.repositories.AnaliseprefermentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnaliseprefermentacaoService {

    @Autowired
    AnaliseprefermentacaoRepository repository;

    public Analiseprefermentacao register(DadosCadastroAnalisePreFermetacao data) {
        var newAnaliseprefermentacao = new Analiseprefermentacao(data);
        repository.save(newAnaliseprefermentacao);
        return newAnaliseprefermentacao;
    }
}
