package com.scanai.api.services;

import com.scanai.api.domain.analiseprefermentacao.Analiseprefermentacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosCadastroAnalisePreFermetacao;
import org.springframework.stereotype.Service;

@Service
public class AnaliseprefermentacaoService {

    public Analiseprefermentacao createAnaliseprefermentacao(DadosCadastroAnalisePreFermetacao data) {
        var newAnaliseprefermentacao = new Analiseprefermentacao(data.fkfuncionario(), data.fkvinho(), data.atotal(), data.acucarRed(), data.ph(), data.so2l(), data.ta());
        return newAnaliseprefermentacao;
    }
}
