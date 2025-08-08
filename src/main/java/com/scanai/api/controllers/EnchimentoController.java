package com.scanai.api.controllers;

import com.scanai.api.domain.enchimento.dto.DadosAtualizarEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosCadastroEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosDetalhamentoEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosListagemEnchimento;
import com.scanai.api.services.implement.EnchimentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/enchimento")
public class EnchimentoController {

    @Autowired
    private EnchimentoService enchimentoService;

    @Transactional
    @PostMapping("/register")
    public DadosDetalhamentoEnchimento register(@RequestBody @Valid DadosCadastroEnchimento dados) {
        return enchimentoService.register(dados);
    }

    @GetMapping("/getAll")
    public List<DadosListagemEnchimento> getAll() {
        return enchimentoService.getAll();
    }

    @GetMapping("/getElement/{id}")
    public DadosDetalhamentoEnchimento getElement(@PathVariable Long id) {
        return enchimentoService.getElement(id);
    }

    @PutMapping("/update")
    @Transactional
    public DadosDetalhamentoEnchimento update(@RequestBody @Valid DadosAtualizarEnchimento dados) {
        return enchimentoService.update(dados);
    }

    @DeleteMapping("/hardDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void hardDelete(@PathVariable Long id) {
        enchimentoService.hardDelete(id);
    }

}