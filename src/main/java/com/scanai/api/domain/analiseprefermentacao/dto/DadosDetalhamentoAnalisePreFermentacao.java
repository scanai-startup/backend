package com.scanai.api.domain.analiseprefermentacao.dto;

import com.scanai.api.domain.analiseprefermentacao.Analiseprefermentacao;

import java.time.LocalDate;

public record DadosDetalhamentoAnalisePreFermentacao(Long id, LocalDate data, Long fkfuncionario, Long fkvinho, float atotal, float acucarRed, int ph, float so2l, float ta) {
    public DadosDetalhamentoAnalisePreFermentacao(Analiseprefermentacao analiseprefermentacao) {
        this(
                analiseprefermentacao.getId(),
                analiseprefermentacao.getData(),
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
