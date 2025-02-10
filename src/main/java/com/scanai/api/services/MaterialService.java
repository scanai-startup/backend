package com.scanai.api.services;

import com.scanai.api.domain.entradamaterial.EntradaMaterial;
import com.scanai.api.domain.material.Material;
import com.scanai.api.domain.material.dto.DadosListagemMaterial;
import com.scanai.api.domain.material.dto.DadosCadastroMaterial;
import com.scanai.api.repositories.EntradaMaterialRepository;
import com.scanai.api.repositories.MaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    MaterialRepository repository;
    @Autowired
    EntradaMaterialRepository entradaMaterialRepository;

    @Transactional
    public Material register(DadosCadastroMaterial dados) {
        Material newMaterial = new Material(dados);
        repository.save(newMaterial);
        return newMaterial;
    }

    public List<DadosListagemMaterial> getAll() {
        List<DadosListagemMaterial> listagemMaterial = new ArrayList<>();

        List<Material> materialList = repository.findAll();

        for (Material material : materialList) {
            List<EntradaMaterial> entradaMaterialList = entradaMaterialRepository.findAllByFkmaterial(material.getId());
            int quantidadeMaterial = 0;
            for (EntradaMaterial entradaMaterial : entradaMaterialList) {
                quantidadeMaterial += entradaMaterial.getQttentrada();
            }
            listagemMaterial.add(new DadosListagemMaterial(material.getId(), material.getNome(), quantidadeMaterial));
        }
        return listagemMaterial;
    }
}
