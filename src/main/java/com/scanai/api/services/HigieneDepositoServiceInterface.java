package com.scanai.api.services;

import com.scanai.api.domain.higienedeposito.Higienedeposito;
import com.scanai.api.domain.higienedeposito.dto.DadosCadastroHigieneDeposito;
import com.scanai.api.domain.higienedeposito.dto.DadosDetalhamentoHigieneDeposito;

public interface HigieneDepositoServiceInterface {

    DadosDetalhamentoHigieneDeposito register(DadosCadastroHigieneDeposito data);
}
