package com.scanai.api.domain.deposito.dto;

import com.scanai.api.domain.deposito.Deposito;

public record DadosListagemDeposito(Long id, String tipo, String numero, float capacidade, boolean valid) {
    public DadosListagemDeposito(Deposito deposito) {
        this(
                deposito.getId(), deposito.getTipodeposito(), deposito.getNumerodeposito(), deposito.getCapacidade(), deposito.isValid()
        );
    }
}
