package com.scanai.api.domain.lotematerial.dto;

import com.scanai.api.domain.lotematerial.Lotematerial;

public record ListLotematerialDTO(String fornecedor, String numerolote) {
    public ListLotematerialDTO(Lotematerial lotematerial){
        this(
                lotematerial.getFornecedor(),
                lotematerial.getNumerolote()
        );
    }
}
