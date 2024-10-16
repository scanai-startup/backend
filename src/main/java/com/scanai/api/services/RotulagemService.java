package com.scanai.api.services;

import com.scanai.api.domain.rotulagem.Rotulagem;
import com.scanai.api.domain.rotulagem.dto.DadosCadastroRotulagem;
import com.scanai.api.repositories.RotulagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RotulagemService {

    @Autowired
    RotulagemRepository repository;

    @Transactional
    public Rotulagem register(DadosCadastroRotulagem dados){
        Rotulagem newRotulagem = new Rotulagem(dados);
        repository.save(newRotulagem);
        return newRotulagem;
    }

}
