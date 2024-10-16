package com.scanai.api.services;

import com.scanai.api.domain.lotematerial.Lotematerial;
import com.scanai.api.domain.lotematerial.dto.DadosCadastroLoteMaterial;
import com.scanai.api.repositories.LotematerialRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class LotematerialService {

    LotematerialRepository repository;

    @Transactional
    public Lotematerial register(DadosCadastroLoteMaterial dados){
        Lotematerial newLotematerial = new Lotematerial(dados);
        repository.save(newLotematerial);
        return newLotematerial;
    }

    public Lotematerial getElement(Long id) {

        return repository.getReferenceById(id);
    }

}
