package com.scanai.api.services.implement;

import com.scanai.api.domain.higienedeposito.Higienedeposito;
import com.scanai.api.domain.higienedeposito.dto.DadosCadastroHigieneDeposito;
import com.scanai.api.domain.higienedeposito.dto.DadosDetalhamentoHigieneDeposito;
import com.scanai.api.repositories.HigieneDepositoRepository;
import com.scanai.api.services.HigieneDepositoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HigieneDepositoService implements HigieneDepositoServiceInterface {

    @Autowired
    HigieneDepositoRepository higieneDepositoRepository;

    public DadosDetalhamentoHigieneDeposito register(DadosCadastroHigieneDeposito data){
        Higienedeposito newHigienedeposito = new Higienedeposito(data);
        higieneDepositoRepository.save(newHigienedeposito);
        return new  DadosDetalhamentoHigieneDeposito(newHigienedeposito);
    }
}
