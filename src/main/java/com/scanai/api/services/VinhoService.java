package com.scanai.api.services;

import com.scanai.api.domain.vinho.DTO.DadosAtualizarVinho;
import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import com.scanai.api.domain.vinho.DTO.DadosDetalhamentoVinho;
import com.scanai.api.domain.vinho.DTO.DadosListagemVinho;
import com.scanai.api.domain.vinho.Vinho;
import com.scanai.api.repositories.VinhoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VinhoService {

    @Autowired
    VinhoRepository repository;

    @Transactional
    public Vinho register(DadosCadastroVinho dados){
        return repository.save(new Vinho(dados));
    }

    public List<DadosListagemVinho> getAll() {
        return repository.findAllByValidTrue().stream().map(DadosListagemVinho::new).toList();
    }

    public Vinho getElement(Long id) {
        return repository.getReferenceById(id);
    }

    @Transactional
    public void hardDelete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void softDelete(Long id) {
        var vinho = getElement(id);
        vinho.setValid(false);
    }
    @Transactional
    public void activate(Long id) {
        var vinho = getElement(id);
        vinho.setValid(true);
    }

    @Transactional
    public DadosDetalhamentoVinho update(DadosAtualizarVinho dados) {
        var vinho = getElement(dados.id());
        vinho.atualizar(dados);
        return new DadosDetalhamentoVinho(vinho);
    }
}
