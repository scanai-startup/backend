package com.scanai.api.services;

import com.scanai.api.domain.analiseprefermentacao.Analiseprefermentacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosCadastroAnalisePreFermetacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosDetalhamentoAnalisePreFermentacao;

public interface AnalisePreFermentacaoServiceInterface {

    DadosDetalhamentoAnalisePreFermentacao register(DadosCadastroAnalisePreFermetacao data);
}
