package com.scanai.api.domain.uva.dto;

import com.scanai.api.domain.uva.Uva;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosDetalhamentoUva(
    Long id,
    Date datachegada,
    int numerotalao,
    int qttcaixa,
    int sanidade,
    int peso,
    String so2,
    int numerolote,
    String tipodevinho,
    String casta,
    Long fkfuncionario,
    Long fkmostro
) {

    public DadosDetalhamentoUva(Uva uva) {
        this(
            uva.getId(),
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
