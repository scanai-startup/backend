package com.scanai.api.domain.viticultor.DTO;

import com.scanai.api.domain.viticultor.Viticultor;

public record DadosDetalhamentoViticultor(String cpf, String nome) {
    public DadosDetalhamentoViticultor(Viticultor viticultor) {
        this(
                viticultor.getCpf(),viticultor.getNome()
        );
    }
}
