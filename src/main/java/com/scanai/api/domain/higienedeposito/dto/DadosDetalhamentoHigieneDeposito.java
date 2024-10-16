package com.scanai.api.domain.higienedeposito.dto;

import com.scanai.api.domain.higienedeposito.Higienedeposito;

import java.time.LocalDate;

public record DadosDetalhamentoHigieneDeposito(Long id, LocalDate datahigiene, Long fkdeposito) {
    public DadosDetalhamentoHigieneDeposito(Higienedeposito higienedeposito){
        this(
                higienedeposito.getId(),
                higienedeposito.getDatahigiene(),
                higienedeposito.getFkdeposito()
        );
    }
}
