package com.scanai.api.services.implement;

import com.scanai.api.domain.entradamaterial.EntradaMaterial;
import com.scanai.api.domain.lotematerial.Lotematerial;
import com.scanai.api.domain.material.Material;
import com.scanai.api.domain.material.dto.DadosListagemMaterial;
import com.scanai.api.domain.material.dto.DadosCadastroMaterial;
import com.scanai.api.repositories.EntradaMaterialRepository;
import com.scanai.api.repositories.LotematerialRepository;
import com.scanai.api.repositories.MaterialRepository;
import com.scanai.api.services.MaterialServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialService implements MaterialServiceInterface {

    @Autowired
    MaterialRepository MaterialRepository;

    @Autowired
    EntradaMaterialRepository entradaMaterialRepository;

    @Autowired
    LotematerialRepository loteMaterialRepository;

    @Transactional
    public Material register(DadosCadastroMaterial dados) {
        Material newMaterial = new Material(dados);
        MaterialRepository.save(newMaterial);
        return newMaterial;
    }

    public List<DadosListagemMaterial> getAll() {
        List<DadosListagemMaterial> listagemMaterial = new ArrayList<>();
        List<Material> materialList = MaterialRepository.findAll();

        for(Material material : materialList){
            int quantidadeTotal = 0;

            //TODO será que da pra simplificar e utilizar uma funcao sql direto para fazer essa querry?
            List<Lotematerial> loteMaterialList = loteMaterialRepository.findAllByFkmaterial(material.getId());
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
