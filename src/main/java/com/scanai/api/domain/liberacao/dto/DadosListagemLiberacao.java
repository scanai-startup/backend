package com.scanai.api.domain.liberacao.dto;

import com.scanai.api.domain.liberacao.Liberacao;

import java.util.Date;

public record DadosListagemLiberacao(Long id,
                                     int qttproduzida,
                                     Date datainicio,
                                     Date datafim,
                                     int gfs,
                                     Long fkrotulagem,
                                     Long fkfuncionario) {

    public DadosListagemLiberacao(Liberacao liberacao){
        this(
            liberacao.getId(),
            liberacao.getQttProduzida(),
            liberacao.getDataInicio(),
            liberacao.getDataFim(),
            liberacao.getGfs(),
            liberacao.getFkrotulagem(),
            liberacao.getFkfuncionario()

        );
    }
}
