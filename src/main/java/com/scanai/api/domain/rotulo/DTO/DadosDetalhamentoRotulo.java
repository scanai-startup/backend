package com.scanai.api.domain.rotulo.DTO;

import com.scanai.api.domain.rotulo.Rotulo;

public record DadosDetalhamentoRotulo(Long id, String nome, String tipo) {
    public DadosDetalhamentoRotulo(Rotulo rotulo) {
        this(
                rotulo.getId(), rotulo.getNome(), rotulo.getTipo()
        );
    }
}
