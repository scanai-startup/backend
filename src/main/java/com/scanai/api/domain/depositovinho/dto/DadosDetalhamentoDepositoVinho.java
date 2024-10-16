package com.scanai.api.domain.depositovinho.dto;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositovinho.Depositovinho;

import java.time.LocalDate;

public record DadosDetalhamentoDepositoVinho(Long id, Long fkmostro, Long fkdeposito, LocalDate datainicio, Long fkfuncionario) {
    public DadosDetalhamentoDepositoVinho(Depositovinho depositovinho){
        this(
                depositovinho.getId(),
                depositovinho.getFkvinho(),
                depositovinho.getFkdeposito(),
                depositovinho.getDatafim(),
                depositovinho.getFkfuncionario()
        );
    }
}
