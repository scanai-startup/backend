package com.scanai.api.services;

import com.scanai.api.domain.uva.Uva;
import com.scanai.api.domain.uva.dto.DadosAtualizarUva;
import com.scanai.api.domain.uva.dto.DadosCadastroUva;
import com.scanai.api.domain.uva.dto.DadosDetalhamentoUva;
import com.scanai.api.domain.uva.dto.DadosListagemUva;
import com.scanai.api.repositories.UvaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UvaService {

    @Autowired
    UvaRepository repository;

    @Transactional
    public Uva register(DadosCadastroUva dados){
        return repository.save(new Uva(dados));
    }

    public Uva getElement(Long id) {
        return repository.getReferenceById(id);
    }

    public List<DadosListagemUva> listAll() {
        return repository.findAllByValidTrue().stream().map(DadosListagemUva::new).toList();
    }

    @Transactional
    public void hardDelete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public DadosDetalhamentoUva update(DadosAtualizarUva dados) {
        var uva = getElement(dados.id());
        uva.atualizar(dados);
        return new DadosDetalhamentoUva(uva);
    }

    @Transactional
    public void softDelete(Long id) {
        var uva = getElement(id);
        uva.inativar();
    }
}