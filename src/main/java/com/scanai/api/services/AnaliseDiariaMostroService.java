package com.scanai.api.services;

import com.scanai.api.domain.analisediariamostro.AnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosAtualizarAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosCadastroAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosDetalhamentoAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosListagemAnaliseDiariaMostro;
import com.scanai.api.repositories.AnaliseDiariaMostroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseDiariaMostroService {

    @Autowired
    AnaliseDiariaMostroRepository repository;

    @Transactional
    public AnaliseDiariaMostro register(DadosCadastroAnaliseDiariaMostro dados){
        return repository.save(new AnaliseDiariaMostro(dados));
    }

    public List<DadosListagemAnaliseDiariaMostro> getAll() {
        return repository.findAll().stream().map(DadosListagemAnaliseDiariaMostro::new).toList();
    }

    public  AnaliseDiariaMostro getElement(Long id) {
        return repository.getReferenceById(id);
    }

    @Transactional
    public void hardDelete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public DadosDetalhamentoAnaliseDiariaMostro update(DadosAtualizarAnaliseDiariaMostro dados) {
        var  AnaliseDiariamostro = getElement(dados.id());
        AnaliseDiariamostro.atualizar(dados);
        return new DadosDetalhamentoAnaliseDiariaMostro(AnaliseDiariamostro);
    }
}
