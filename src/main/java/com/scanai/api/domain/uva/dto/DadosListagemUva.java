package com.scanai.api.domain.uva.dto;

import com.scanai.api.domain.uva.Uva;

import java.util.Date;

public record DadosListagemUva(
        Long id,
        Boolean valid,
        Date datachegada,
        int numerotalao,
        int sanidade,
        int peso,
        String so2,
        int numerolote,
        String tipovinho,
        String casta,
        Long fkviticultor,
        Long fkfuncionario
) {
    public DadosListagemUva(Uva uva){
        this(
            uva.getId(),
            uva.getValid(),
            uva.getDatachegada(),
            uva.getNumerotalao(),
            uva.getSanidade(),
            uva.getPeso(),
            uva.getSo2(),
            uva.getNumerolote(),
            uva.getTipovinho(),
            uva.getCasta(),
            uva.getFkviticultor(),
            uva.getFkfuncionario()
        );
    }
}
