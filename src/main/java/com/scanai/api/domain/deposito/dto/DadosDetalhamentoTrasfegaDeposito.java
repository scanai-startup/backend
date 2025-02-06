package com.scanai.api.domain.deposito.dto;


public record DadosDetalhamentoTrasfegaDeposito(String tipo, Long idLiquido, Long idDepositoDestino, Long fkfuncionario, String mensagem) {
}
