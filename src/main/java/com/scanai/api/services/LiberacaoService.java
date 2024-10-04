package com.scanai.api.services;

import com.scanai.api.domain.liberacao.Liberacao;
import com.scanai.api.domain.liberacao.dto.DadosAtualizarLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosCadastroLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosDetalhamentoLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosListagemLiberacao;
import com.scanai.api.repositories.LiberacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiberacaoService {

    @Autowired
    LiberacaoRepository repository;

    @Transactional
    public Liberacao register(DadosCadastroLiberacao dados){
        return repository.save(new Liberacao(dados));
    }

    public List<DadosListagemLiberacao> listAll() {
        return repository.findAll().stream().map(DadosListagemLiberacao::new).toList();
    }

    public Liberacao getElement(Long id) {
        return repository.getReferenceById(id);
    }

    @Transactional
    public void hardDelete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public DadosDetalhamentoLiberacao update(DadosAtualizarLiberacao dados) {
        var liberacao = getElement(dados.id());
        liberacao.atualizar(dados);
        return new DadosDetalhamentoLiberacao(liberacao);
    }
}
