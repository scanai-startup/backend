package com.scanai.api.services;

import com.scanai.api.domain.analiseprefermentacao.Analiseprefermentacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosCadastroAnalisePreFermetacao;

public interface AnalisePreFermentacaoServiceInterface {

    Analiseprefermentacao register(DadosCadastroAnalisePreFermetacao data);
}
