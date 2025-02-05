package com.scanai.api.services;

import com.scanai.api.domain.entradamaterial.EntradaMaterial;
import com.scanai.api.domain.entradamaterial.dto.DadosCadastroEntradaMaterial;
import com.scanai.api.domain.entradamaterial.dto.DadosListagemEntradaMaterial;
import com.scanai.api.domain.material.dto.DadosListagemMaterial;
import com.scanai.api.repositories.EntradaMaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaMaterialService {

    @Autowired
    private EntradaMaterialRepository repository;

    @Transactional
    public EntradaMaterial register(DadosCadastroEntradaMaterial data){
        EntradaMaterial newEntradaMaterial = new EntradaMaterial(data);
        repository.save(newEntradaMaterial);
        return newEntradaMaterial;
    }
    public List<DadosListagemEntradaMaterial> getAll(){
        List<EntradaMaterial> entradaMaterialList = repository.findAll();
        return entradaMaterialList.stream().map(DadosListagemEntradaMaterial::new).toList();
    }
}
