package com.scanai.api.domain.depositopedecuba.dto;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;

import java.time.LocalDate;

public record DadosDetalhamentoDepositoPeDeCuba(Long id, Long fkmostro, Long fkdeposito, LocalDate datainicio, Long fkfuncionario) {
    public DadosDetalhamentoDepositoPeDeCuba(Depositopedecuba depositopedecuba){
        this(
                depositopedecuba.getId(),
                depositopedecuba.getFkpedecuba(),
                depositopedecuba.getFkdeposito(),
                depositopedecuba.getDatafim(),
                depositopedecuba.getFkfuncionario()
        );
    }
}
