package com.scanai.api.services.implement;

import com.scanai.api.domain.lotematerial.Lotematerial;
import com.scanai.api.domain.lotematerial.dto.DadosCadastroLoteMaterial;
import com.scanai.api.repositories.LotematerialRepository;
import com.scanai.api.services.LoteMaterialServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoteMaterialService implements LoteMaterialServiceInterface {

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
