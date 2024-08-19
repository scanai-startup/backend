package com.scanai.api.domain.higienedeposito.dto;

import com.scanai.api.domain.higienedeposito.Higienedeposito;

import java.time.LocalDate;

public record ResListHigienedeposito(LocalDate datahigiene, Long fkdeposito) {

    public ResListHigienedeposito(Higienedeposito higienedeposito){
        this(
                higienedeposito.getDatahigiene(),
                higienedeposito.getFkdeposito()
        );
    }
}
