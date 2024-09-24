package com.scanai.api.controllers;

import com.scanai.api.domain.enchimento.dto.DadosAtualizarEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosCadastroEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosDetalhamentoEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosListagemEnchimento;
import com.scanai.api.services.EnchimentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/enchimento")
public class EnchimentoController {

    private final EnchimentoService enchimentoService;

    public EnchimentoController(EnchimentoService enchimentoService) {
        this.enchimentoService = enchimentoService;
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoEnchimento> register(@RequestBody @Valid DadosCadastroEnchimento dados, UriComponentsBuilder builder) {
        var enchimento = enchimentoService.register(dados);
        var uri = builder.path("/enchimento/{id}").buildAndExpand(enchimento.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEnchimento(enchimento));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DadosListagemEnchimento>> getAll() {
        var lista = enchimentoService.listAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/getElement/{id}")
    public ResponseEntity<DadosDetalhamentoEnchimento> getElement(@PathVariable Long id) {
        var enchimento = enchimentoService.getElement(id);
        return ResponseEntity.ok(new DadosDetalhamentoEnchimento(enchimento));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DadosDetalhamentoEnchimento> update(@RequestBody @Valid DadosAtualizarEnchimento dados) {
        var enchimento = enchimentoService.update(dados);
        return ResponseEntity.ok(enchimento);
    }

    @DeleteMapping("/hardDelete/{id}")
    @Transactional
    public ResponseEntity<?> hardDelete(@PathVariable Long id) {
        enchimentoService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

}