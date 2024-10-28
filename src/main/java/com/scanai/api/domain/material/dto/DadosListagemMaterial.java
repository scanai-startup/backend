package com.scanai.api.domain.material.dto;

import com.scanai.api.domain.material.Material;

public record DadosListagemMaterial(String nome) {
    public DadosListagemMaterial(Material material){
        this(
                material.getNome()
        );
    }
}
