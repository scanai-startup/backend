package com.scanai.api.domain.uva.dto;

import com.scanai.api.domain.uva.Uva;

import java.util.Date;

public record DadosListagemUva(
        Long id,
        Boolean valid,
        Date datachegada,
        int numerotalao,
        int qttcaixa,
        int sanidade,
        int peso,
        String so2,
        int numerolote,
        String tipovinho,
        String casta,
        Long fkfuncionario,
        Long fkmostro
) {
    public DadosListagemUva(Uva uva){
        this(
            uva.getId(),
            uva.getValid(),
            uva.getDatachegada(),
            uva.getNumerotalao(),
                uva.getQttcaixa(),
            uva.getSanidade(),
            uva.getPeso(),
            uva.getSo2(),
            uva.getNumerolote(),
            uva.getTipovinho(),
            uva.getCasta(),
            uva.getFkfuncionario(),
                uva.getFkmostro()
        );
    }
}
