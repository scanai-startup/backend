package com.scanai.api.domain.higienedeposito.dto;

import com.scanai.api.domain.higienedeposito.Higienedeposito;

import java.time.LocalDate;

public record DadosListagemHigieneDeposito(LocalDate datahigiene, Long fkdeposito) {

    public DadosListagemHigieneDeposito(Higienedeposito higienedeposito){
        this(
                higienedeposito.getDatahigiene(),
                higienedeposito.getFkdeposito()
        );
    }
}
