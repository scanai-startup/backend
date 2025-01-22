package com.scanai.api.domain.deposito.dto;

public interface DadosInformacoesDepositos {
    String getConteudo();
    Double getTemperatura();
    Double getDensidade();
    Double getPressao();
    String getIdDeposito();
    String getNumeroDeposito();
    String getTipoDeposito();
    Float getCapacidadeDeposito();
    Float getVolumeConteudo();
    Long getIdConteudo();

}
