package com.scanai.api.services;

import com.scanai.api.domain.material.Material;
import com.scanai.api.domain.material.dto.DadosListagemMaterial;
import com.scanai.api.domain.material.dto.DadosCadastroMaterial;
import com.scanai.api.repositories.MaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    MaterialRepository repository;

    @Transactional
    public Material register(DadosCadastroMaterial dados){
        Material newMaterial = new Material(dados);
        repository.save(newMaterial);
        return newMaterial;
    }

    public Material getElement(Long id) {

        return repository.getReferenceById(id);
    }

    public List<DadosListagemMaterial> getAll() {
        return repository.findAll().stream().map(DadosListagemMaterial::new).toList();
    }
}
