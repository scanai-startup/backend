package com.scanai.api.domain.deposito.dto;

import com.scanai.api.domain.deposito.Deposito;

public record DadosDetalhamentoDeposito(String tipo, String numero, boolean valid) {
    public DadosDetalhamentoDeposito(Deposito deposito) {
        this(
                deposito.getTipodeposito(), deposito.getNumerodeposito(), deposito.isValid()
        );
    }
}
