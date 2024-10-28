package com.scanai.api.services;

import com.scanai.api.domain.entradamaterial.EntradaMaterial;
import com.scanai.api.domain.entradamaterial.dto.DadosCadastroEntradaMaterial;
import com.scanai.api.repositories.EntradaMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntradaMaterialService {

    @Autowired
    private EntradaMaterialRepository repository;

    public EntradaMaterial register(DadosCadastroEntradaMaterial data){
        EntradaMaterial newEntradaMaterial = new EntradaMaterial(data);
        repository.save(newEntradaMaterial);
        return newEntradaMaterial;
    }
}
