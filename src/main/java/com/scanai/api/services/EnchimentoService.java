package com.scanai.api.services;

import com.scanai.api.domain.enchimento.Enchimento;
import com.scanai.api.domain.enchimento.dto.DadosAtualizarEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosCadastroEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosDetalhamentoEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosListagemEnchimento;
import com.scanai.api.domain.vinho.DTO.DadosAtualizarVinho;
import com.scanai.api.domain.vinho.Vinho;
import com.scanai.api.repositories.EnchimentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnchimentoService {

    @Autowired
    EnchimentoRepository repository;

    @Autowired
    VinhoService vinhoService;

    @Transactional
    public Enchimento register(DadosCadastroEnchimento dados) {
        Vinho vinho = vinhoService.getElement(dados.fkvinho());
        vinho.setVolume(vinho.getVolume() - dados.volumeTrasfega());
        vinho.setDatafimfermentacao(LocalDate.now());
        vinhoService.update(new DadosAtualizarVinho(vinho));

        return repository.save(new Enchimento(dados));
    }

    public List<DadosListagemEnchimento> getAll() {
        return repository.findAll().stream().map(DadosListagemEnchimento::new).toList();
    }

    public Enchimento getElement(Long id) {
        return repository.getReferenceById(id);
    }

    @Transactional
    public void hardDelete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public DadosDetalhamentoEnchimento update(DadosAtualizarEnchimento dados) {
        var enchimento = getElement(dados.id());
        enchimento.atualizar(dados);
        return new DadosDetalhamentoEnchimento(enchimento);
    }
}