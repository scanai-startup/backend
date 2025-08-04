package com.scanai.api.services.implement;

import com.scanai.api.domain.analiseprefermentacao.Analiseprefermentacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosCadastroAnalisePreFermetacao;
import com.scanai.api.repositories.AnaliseprefermentacaoRepository;
import com.scanai.api.services.AnalisePreFermentacaoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalisePreFermentacaoService implements AnalisePreFermentacaoServiceInterface {

    @Autowired
    AnaliseprefermentacaoRepository analisePreFermentacaoRepository;

    public Analiseprefermentacao register(DadosCadastroAnalisePreFermetacao data) {
        var newAnaliseprefermentacao = new Analiseprefermentacao(data);
        analisePreFermentacaoRepository.save(newAnaliseprefermentacao);
        return newAnaliseprefermentacao;
    }
}
