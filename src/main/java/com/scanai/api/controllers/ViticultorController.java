package com.scanai.api.controllers;


import com.scanai.api.domain.viticultor.DTO.DadosAtualizarViticultor;
import com.scanai.api.domain.viticultor.DTO.DadosCadatroViticultor;
import com.scanai.api.domain.viticultor.DTO.DadosDetalhamentoViticultor;
import com.scanai.api.domain.viticultor.DTO.DadosListagemViticultor;
import com.scanai.api.domain.viticultor.Viticultor;
import com.scanai.api.repositories.ViticultorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/viticultor")
public class ViticultorController {

    @Autowired
    private ViticultorRepository repository;

    @Transactional
    @PostMapping()
    public ResponseEntity<DadosDetalhamentoViticultor> cadastrarVinho(@RequestBody @Valid DadosCadatroViticultor dados, UriComponentsBuilder builder){ //DadosCadastroRemedio é um DTO construido nu

        var viticultor = repository.save(new Viticultor(dados)); // função do proprio jpa
        // o DTO passado como argumento é lido no construtor, que retorna os atributos
        var uri = builder.path("/viticultor/{id}").buildAndExpand(viticultor.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DadosDetalhamentoViticultor(viticultor));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DadosListagemViticultor>> listarViticultor(){
        var lista = repository.findAll().stream().map(DadosListagemViticultor::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity<DadosDetalhamentoViticultor> detalharViticultor(@PathVariable Long id){
        var viticultor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoViticultor(viticultor));
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<DadosDetalhamentoViticultor> atualizarViticultor(@RequestBody DadosAtualizarViticultor dados){
        var viticultor = repository.getReferenceById(dados.id());
        viticultor.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoViticultor(viticultor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarViticultor(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
