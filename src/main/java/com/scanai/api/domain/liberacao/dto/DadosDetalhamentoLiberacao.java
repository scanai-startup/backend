package com.scanai.api.domain.liberacao.dto;

import com.scanai.api.domain.liberacao.Liberacao;

import java.time.LocalDate;

public record DadosDetalhamentoLiberacao (Long id,
                                          int qttproduzida,
                                          LocalDate datainicio,
                                          LocalDate datafim,
                                          int gfs,
                                          Long fkrotulagem,
                                          Long fkfuncionario){

    public DadosDetalhamentoLiberacao(Liberacao liberacao){
        this(
                liberacao.getId(),
                liberacao.getQttproduzida(),
                liberacao.getDatainicio(),
                liberacao.getDatafim(),
                liberacao.getGfs(),
                liberacao.getFkrotulagem(),
                liberacao.getFkfuncionario()
        );
    }
}
