package com.scanai.api.services;

import com.scanai.api.domain.entradamaterial.EntradaMaterial;
import com.scanai.api.domain.lotematerial.Lotematerial;
import com.scanai.api.domain.material.Material;
import com.scanai.api.domain.material.dto.DadosListagemMaterial;
import com.scanai.api.domain.material.dto.DadosCadastroMaterial;
import com.scanai.api.repositories.EntradaMaterialRepository;
import com.scanai.api.repositories.LotematerialRepository;
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

    @Autowired
    LotematerialRepository lotematerialRepository;

    @Transactional
    public Material register(DadosCadastroMaterial dados) {
        Material newMaterial = new Material(dados);
        repository.save(newMaterial);
        return newMaterial;
    }

    public List<DadosListagemMaterial> getAll() {
        List<DadosListagemMaterial> listagemMaterial = new ArrayList<>();
        List<Material> materialList = repository.findAll();

        for(Material material : materialList){
            int quantidadeTotal = 0;

            List<Lotematerial> loteMaterialList = lotematerialRepository.findAllByFkmaterial(material.getId());
            for (Lotematerial lotematerial : loteMaterialList) {
                List<EntradaMaterial> entradaMaterialList = entradaMaterialRepository.findAllByFklotematerial(lotematerial.getId());
                for(EntradaMaterial entradaMaterial : entradaMaterialList){
                    quantidadeTotal += entradaMaterial.getQttentrada();
                }
            }
            listagemMaterial.add(new DadosListagemMaterial(material.getId(), material.getNome(), quantidadeTotal));
        }


        return listagemMaterial;
    }
}
