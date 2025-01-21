package com.scanai.api.domain.deposito.dto;

public interface DadosInformacoesDepositos {
    String getConteudo();
    Double getTemperatura();
    Double getDensidade();
    Double getPressao();
    String getIdDeposito();
    Float getCapacidade();
    Float getVolume(); // Alterado para Float
    Long getIdConteudo();

}
