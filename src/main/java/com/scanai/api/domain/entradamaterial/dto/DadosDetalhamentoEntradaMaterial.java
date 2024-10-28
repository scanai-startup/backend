package com.scanai.api.domain.entradamaterial.dto;


import com.scanai.api.domain.entradamaterial.EntradaMaterial;

import java.util.Date;

public record DadosDetalhamentoEntradaMaterial(
        Long id,
        Long fkmaterial,
        int qttentrada,
        float valorunidade,
        Date data) {

    public DadosDetalhamentoEntradaMaterial(EntradaMaterial entradaMaterial){
        this(
                entradaMaterial.getId(),
                entradaMaterial.getFkmaterial(),
                entradaMaterial.getQttentrada(),
                entradaMaterial.getValorunidade(),
                entradaMaterial.getData()
        );
    }
}

