package com.scanai.api.domain.viticultor.DTO;

import com.scanai.api.domain.viticultor.Viticultor;

public record DadosListagemViticultor(String cpf, String nome) {

    public DadosListagemViticultor(Viticultor viticultor){
        this(
                viticultor.getCpf(), viticultor.getNome()
        );
    }
}
