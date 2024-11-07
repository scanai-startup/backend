package com.scanai.api.domain.depositomostro.dto;

import com.scanai.api.domain.depositomostro.DepositoMostro;

import java.time.LocalDate;

public record DadosDetalhamentoDepositoMostro(Long id, Long fkmostro, Long fkdeposito, LocalDate datainicio, Long fkfuncionario) {
    public DadosDetalhamentoDepositoMostro(DepositoMostro depositomostro){
        this(
                depositomostro.getId(),
                depositomostro.getFkmostro(),
                depositomostro.getFkdeposito(),
                depositomostro.getDatafim(),
                depositomostro.getFkfuncionario()
        );
    }
}
