package com.scanai.api.scanai.domain.uva.dto;

import com.scanai.api.scanai.domain.uva.Uva;

import java.util.Date;

public record DadosDetalhamentoUva(
    Long id,
    Date datachegada,
    int nuumerotalao,
    int sanidade,
    int peso,
    String so2,
    int numerolote,
    String tipodevinho,
    String casta
) {

    public DadosDetalhamentoUva(Uva uva) {
        this(
            uva.getId(),
            uva.getDatachegada(),
            uva.getNumerolote(),
            uva.getSanidade(),
            uva.getPeso(),
            uva.getSo2(),
            uva.getNumerolote(),
            uva.getTipovinho(),
            uva.getCasta()
        );
    }

}
