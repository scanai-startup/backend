package com.scanai.api.domain.produtoadcpedecuba;

public enum UnidadeDeMedida {

    KG("kilograma"),
    G("grama"),
    L("litro"),
    ML("mililitro");


    private String unidade;

    UnidadeDeMedida(String unidade){
        this.unidade = unidade;
    }

    public String getUnidade(){
        return unidade;
    }
}
