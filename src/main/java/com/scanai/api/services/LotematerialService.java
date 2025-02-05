package com.scanai.api.services;

import com.scanai.api.domain.lotematerial.Lotematerial;
import com.scanai.api.domain.lotematerial.dto.DadosCadastroLoteMaterial;
import com.scanai.api.repositories.LotematerialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LotematerialService {

    @Autowired
    LotematerialRepository repository;


    @Transactional
    public Lotematerial register(DadosCadastroLoteMaterial dados){
        Lotematerial newLotematerial = new Lotematerial(dados);
        repository.save(newLotematerial);
        return newLotematerial;
    }

    public List<Lotematerial> getAll(){
        return repository.findAll();
    }
}
