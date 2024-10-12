package com.scanai.api.services;

import com.scanai.api.domain.rotulo.DTO.DadosAtualizarRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosCadastroRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosDetalhamentoRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosListagemRotulo;
import com.scanai.api.domain.rotulo.Rotulo;
import com.scanai.api.repositories.RotuloRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RotuloService {

    @Autowired
    RotuloRepository repository;

    @Transactional
    public Rotulo register(DadosCadastroRotulo dados){
        return repository.save(new Rotulo(dados));
    }

    public List<DadosListagemRotulo> listAll(){
        return repository.findAllByValidTrue().stream().map(DadosListagemRotulo::new).toList();
    }

    public Rotulo getElement(Long id){
        return repository.getReferenceById(id);

    }

    @Transactional
    public void hardDelete(Long id){
        repository.deleteById(id);

    }

    @Transactional
    public DadosDetalhamentoRotulo update(DadosAtualizarRotulo dados) {
        var rotulo = getElement(dados.id());
        rotulo.update(dados);
        return new DadosDetalhamentoRotulo(rotulo);
    }
}
