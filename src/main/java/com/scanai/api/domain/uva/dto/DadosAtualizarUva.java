package com.scanai.api.domain.uva.dto;

import java.util.Date;

public record DadosAtualizarUva(
        Long id,
        Date datachegada,
        int numerotalao,
        int sanidade,
        int peso,
        String so2,
        int numerolote,
        String tipodevinho,
        String casta) {
}
