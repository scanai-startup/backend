package com.scanai.api.services;

import com.scanai.api.domain.higienedeposito.Higienedeposito;
import com.scanai.api.domain.higienedeposito.dto.DadosCadastroHigieneDeposito;

public interface HigieneDepositoServiceInterface {

    Higienedeposito register(DadosCadastroHigieneDeposito data);
}
