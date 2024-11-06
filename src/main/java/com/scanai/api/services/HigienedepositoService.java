package com.scanai.api.services;

import com.scanai.api.domain.higienedeposito.Higienedeposito;
import com.scanai.api.domain.higienedeposito.dto.DadosCadastroHigieneDeposito;
import com.scanai.api.repositories.HigienedepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HigienedepositoService {

    @Autowired
    HigienedepositoRepository repository;

    public Higienedeposito register(DadosCadastroHigieneDeposito data){
        Higienedeposito newHigienedeposito = new Higienedeposito(data);
        repository.save(newHigienedeposito);
        return newHigienedeposito;
    }
}
