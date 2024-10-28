package com.scanai.api.domain.material.dto;

import com.scanai.api.domain.material.Material;


public record DadosDetalhamentoMaterial(Long id, String nome) {
    public DadosDetalhamentoMaterial(Material material){
        this(
                material.getId(),
                material.getNome()
        );
    }
}
