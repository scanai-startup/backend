package com.scanai.api.domain.lotematerial.dto;

import com.scanai.api.domain.lotematerial.Lotematerial;


public record DadosDetalhamentoLoteMaterial(Long id, String fornecedor, String numerolote) {
    public DadosDetalhamentoLoteMaterial(Lotematerial lotematerial){
        this(
                lotematerial.getId(),
                lotematerial.getFornecedor(),
                lotematerial.getNumerolote()
        );
    }
}
