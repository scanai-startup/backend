package com.scanai.api.domain.lotematerial.dto;

import com.scanai.api.domain.lotematerial.Lotematerial;


public record DadosDetalhamentoLoteMaterial(Long id, Long fkmaterial, String fornecedor, String numerolote) {
    public DadosDetalhamentoLoteMaterial(Lotematerial lotematerial){
        this(
                lotematerial.getId(),
                lotematerial.getFkmaterial(),
                lotematerial.getFornecedor(),
                lotematerial.getNumerolote()
        );
    }
}
