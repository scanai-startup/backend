package com.scanai.api.services.implement;

import com.scanai.api.domain.analiseprefermentacao.Analiseprefermentacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosCadastroAnalisePreFermetacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosDetalhamentoAnalisePreFermentacao;
import com.scanai.api.repositories.AnalisePreFermentacaoRepository;
import com.scanai.api.services.AnalisePreFermentacaoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalisePreFermentacaoService implements AnalisePreFermentacaoServiceInterface {

    @Autowired
    AnalisePreFermentacaoRepository analisePreFermentacaoRepository;

    public DadosDetalhamentoAnalisePreFermentacao register(DadosCadastroAnalisePreFermetacao data) {
        return new DadosDetalhamentoAnalisePreFermentacao(analisePreFermentacaoRepository.save(new Analiseprefermentacao(data)));
    }
}
