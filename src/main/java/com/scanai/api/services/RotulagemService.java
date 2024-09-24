package com.scanai.api.services;

import com.scanai.api.domain.rotulagem.Rotulagem;
import com.scanai.api.domain.rotulagem.dto.RegisterRotulagemDTO;
import com.scanai.api.domain.rotulo.DTO.DadosCadastroRotulo;
import com.scanai.api.domain.rotulo.Rotulo;
import com.scanai.api.repositories.RotulagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RotulagemService {

    @Autowired
    RotulagemRepository repository;

    @Transactional
    public Rotulagem register(RegisterRotulagemDTO dados){
        return repository.save(new Rotulagem(dados));
    }

}
