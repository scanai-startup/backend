package com.scanai.api.domain.deposito.dto;

public interface DadosInformacoesDepositos {
    Long getIdDeposito();
    Long getIdConteudo();
    String getConteudo();
    String getDeposito();
    Double getTemperatura();
    Double getDensidade();
    Double getPressao();
}
