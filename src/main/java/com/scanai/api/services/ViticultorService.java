package com.scanai.api.services;

import com.scanai.api.domain.viticultor.DTO.DadosAtualizarViticultor;
import com.scanai.api.domain.viticultor.DTO.DadosDetalhamentoViticultor;
import com.scanai.api.domain.viticultor.DTO.DadosListagemViticultor;
import com.scanai.api.domain.viticultor.DTO.DadosCadastroViticultor;
import com.scanai.api.domain.viticultor.Viticultor;
import com.scanai.api.repositories.ViticultorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViticultorService {

    @Autowired
    ViticultorRepository repository;

    @Transactional
    public Viticultor register(DadosCadastroViticultor dados){
        return repository.save(new Viticultor(dados));
    }

    public Viticultor getElement(Long id) {
        return repository.getReferenceById(id);
    }

    public List<DadosListagemViticultor> getAll() {
        return repository.findAll().stream().map(DadosListagemViticultor::new).toList();
    }

    @Transactional
    public void hardDelete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public DadosDetalhamentoViticultor update(DadosAtualizarViticultor dados) {
        var viticultor = getElement(dados.id());
        viticultor.atualizar(dados);
        return new DadosDetalhamentoViticultor(viticultor);
    }

}
