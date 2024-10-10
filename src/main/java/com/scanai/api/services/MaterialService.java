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

        return repository.save(new Material(dados));
    }

    public Material getElement(Long id) {

        return repository.getReferenceById(id);
    }

    public List<DadosListagemMaterial> listAll() {
        return repository.findAll().stream().map(DadosListagemMaterial::new).toList();
    }
}
