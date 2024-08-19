package com.scanai.api.services;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.RegisterDepositoDTO;
import com.scanai.api.domain.higienedeposito.Higienedeposito;
import com.scanai.api.domain.higienedeposito.dto.RegisterHigienedepositoDTO;
import com.scanai.api.repositories.HigienedepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HigienedepositoService {

    @Autowired
    HigienedepositoRepository repository;

    public Higienedeposito createHigienedeposito(RegisterHigienedepositoDTO data){
        return new Higienedeposito(data.datahigiene(), data.fkdeposito());
    }
}
