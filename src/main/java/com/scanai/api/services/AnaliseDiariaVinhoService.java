package com.scanai.api.services;

import com.scanai.api.domain.analisediariavinho.AnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosAtualizarAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosCadastroAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosDetalhamentoAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosListagemAnaliseDiariaVinho;
import com.scanai.api.domain.vinho.DTO.DadosAtualizarVinho;
import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import com.scanai.api.domain.vinho.DTO.DadosDetalhamentoVinho;
import com.scanai.api.domain.vinho.DTO.DadosListagemVinho;
import com.scanai.api.domain.vinho.Vinho;
import com.scanai.api.repositories.AnaliseDiariaVinhoRepository;
import com.scanai.api.repositories.VinhoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseDiariaVinhoService {

    @Autowired
    AnaliseDiariaVinhoRepository repository;

    @Transactional
    public AnaliseDiariaVinho register(DadosCadastroAnaliseDiariaVinho dados){
        return repository.save(new AnaliseDiariaVinho(dados));
    }

    public List<DadosListagemAnaliseDiariaVinho> listAll() {
        return repository.findAll().stream().map(DadosListagemAnaliseDiariaVinho::new).toList();
    }

    public  AnaliseDiariaVinho getElement(Long id) {
        return repository.getReferenceById(id);
    }

    @Transactional
    public void hardDelete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public DadosDetalhamentoAnaliseDiariaVinho update(DadosAtualizarAnaliseDiariaVinho dados) {
        var  AnaliseDiariavinho = getElement(dados.id());
        AnaliseDiariavinho.atualizar(dados);
        return new DadosDetalhamentoAnaliseDiariaVinho(AnaliseDiariavinho);
    }
}
