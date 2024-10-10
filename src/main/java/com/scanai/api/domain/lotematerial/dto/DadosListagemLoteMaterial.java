package com.scanai.api.domain.lotematerial.dto;

import com.scanai.api.domain.lotematerial.Lotematerial;

public record DadosListagemLoteMaterial(String fornecedor, String numerolote) {
    public DadosListagemLoteMaterial(Lotematerial lotematerial){
        this(
                lotematerial.getFornecedor(),
                lotematerial.getNumerolote()
        );
    }
}
