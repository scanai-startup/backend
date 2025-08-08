package com.scanai.api.services.implement;

import com.scanai.api.domain.rotulagem.Rotulagem;
import com.scanai.api.domain.rotulagem.dto.DadosCadastroRotulagem;
import com.scanai.api.domain.rotulagem.dto.DadosDetalhamentoRotulagem;
import com.scanai.api.repositories.RotulagemRepository;
import com.scanai.api.services.RotulagemServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RotulagemService implements RotulagemServiceInterface {

    @Autowired
    RotulagemRepository rotulagemRepository;

    @Transactional
    public DadosDetalhamentoRotulagem register(DadosCadastroRotulagem dados){
        return new DadosDetalhamentoRotulagem(rotulagemRepository.save(new Rotulagem(dados)));
    }

}
