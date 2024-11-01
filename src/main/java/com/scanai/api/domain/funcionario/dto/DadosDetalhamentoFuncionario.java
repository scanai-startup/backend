package com.scanai.api.domain.funcionario.dto;


import com.scanai.api.domain.funcionario.Funcionario;


public record DadosDetalhamentoFuncionario(
        String nome,
        String matricula) {

    public DadosDetalhamentoFuncionario(Funcionario funcionario){
        this(
                funcionario.getNome(),
                funcionario.getMatricula()
        );
    }
}