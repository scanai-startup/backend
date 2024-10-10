package com.scanai.api.controllers;

import com.scanai.api.domain.liberacao.dto.DadosAtualizarLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosCadastroLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosDetalhamentoLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosListagemLiberacao;
import com.scanai.api.services.LiberacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/liberacao")

public class LiberacaoController {
    LiberacaoService liberacaoService;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoLiberacao> register(@RequestBody @Valid DadosCadastroLiberacao dados, UriComponentsBuilder builder){ //DadosCadastroRemedio é um DTO construido nu

        var liberacao = liberacaoService.register(dados);// função do proprio jpa
        // o DTO passado como argumento é lido no construtor, que retorna os atributos
        var uri = builder.path("/liberacao/{id}").buildAndExpand(liberacao.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DadosDetalhamentoLiberacao(liberacao));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DadosListagemLiberacao>> getAll(){
        var lista = liberacaoService.getAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/getElement/{id}")
    public ResponseEntity<DadosDetalhamentoLiberacao> getElement(@PathVariable Long id){
        var liberacao = liberacaoService.getElement(id);
        return ResponseEntity.ok(new DadosDetalhamentoLiberacao(liberacao));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DadosDetalhamentoLiberacao> update(@RequestBody DadosAtualizarLiberacao dados){
        var liberacao = liberacaoService.update(dados);
        return ResponseEntity.ok(liberacao);
    }

    @DeleteMapping("hardDelete/{id}")
    @Transactional
    public ResponseEntity<?> hardDelete(@PathVariable Long id){
        liberacaoService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

}
