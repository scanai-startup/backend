package com.scanai.api.services;

import com.scanai.api.domain.mostrovinho.MostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosAtualizarMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosCadastroMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosDetalhamentoMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosListagemMostroVinho;
import com.scanai.api.repositories.MostroVinhoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MostroVinhoService {

    MostroVinhoRepository repository;
    @Transactional

    public MostroVinho register(DadosCadastroMostroVinho dados){

        return repository.save(new MostroVinho(dados));
    }
    public MostroVinho getElement(Long id) {

        return repository.getReferenceById(id);
    }

    public List<DadosListagemMostroVinho> getAll() {
        return repository.findAll().stream().map(DadosListagemMostroVinho::new).toList();
    }

    @Transactional
    public void hardDelete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public DadosDetalhamentoMostroVinho update(DadosAtualizarMostroVinho dados) {
        var mostroVinho = getElement(dados.id());
        mostroVinho.atualizar(dados);
        return new DadosDetalhamentoMostroVinho(mostroVinho);
    }

    public void softDelete(Long id) {
    }
}
