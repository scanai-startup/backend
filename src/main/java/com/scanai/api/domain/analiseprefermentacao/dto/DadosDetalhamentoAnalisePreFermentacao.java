package com.scanai.api.domain.analiseprefermentacao.dto;

import com.scanai.api.domain.analiseprefermentacao.Analiseprefermentacao;

public record DadosDetalhamentoAnalisePreFermentacao(Long id, Long fkfuncionario, Long fkvinho, float atotal, float acucarRed, int ph, float so2l, float ta) {
    public DadosDetalhamentoAnalisePreFermentacao(Analiseprefermentacao analiseprefermentacao) {
        this(
                analiseprefermentacao.getId(),
                analiseprefermentacao.getFkfuncionario(),
                analiseprefermentacao.getFkvinho(),
                analiseprefermentacao.getAtotal(),
                analiseprefermentacao.getAcucarRed(),
                analiseprefermentacao.getPh(),
                analiseprefermentacao.getSo2l(),
                analiseprefermentacao.getTa()
        );
    }
}
