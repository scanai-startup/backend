package com.scanai.api.services.implement;

import com.scanai.api.domain.entradamaterial.EntradaMaterial;
import com.scanai.api.domain.entradamaterial.dto.DadosCadastroEntradaMaterial;
import com.scanai.api.domain.entradamaterial.dto.DadosListagemEntradaMaterial;
import com.scanai.api.repositories.EntradaMaterialRepository;
import com.scanai.api.services.EntradaMaterialServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaMaterialService implements EntradaMaterialServiceInterface {

    @Autowired
    private EntradaMaterialRepository entradaMaterialRepository;

    @Transactional
    public EntradaMaterial register(DadosCadastroEntradaMaterial data){
        EntradaMaterial newEntradaMaterial = new EntradaMaterial(data);
        entradaMaterialRepository.save(newEntradaMaterial);
        return newEntradaMaterial;
    }
    public List<DadosListagemEntradaMaterial> getAll(){
        List<EntradaMaterial> entradaMaterialList = entradaMaterialRepository.findAll();
        return entradaMaterialList.stream().map(DadosListagemEntradaMaterial::new).toList();
    }
}
