package com.scanai.api.scanai.controllers;

import com.scanai.api.scanai.domain.uva.dto.DadosAtualizarUva;
import com.scanai.api.scanai.domain.uva.dto.DadosCadastroUva;
import com.scanai.api.scanai.domain.uva.dto.DadosDetalhamentoUva;
import com.scanai.api.scanai.domain.uva.dto.DadosListagemUva;
import com.scanai.api.scanai.domain.uva.Uva;
import com.scanai.api.scanai.repositories.UvaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/uva")
public class UvaController {
    @Autowired //o autowired instancia a classe automaticamente
    private UvaRepository repository;

    @PostMapping()
    @Transactional
    public ResponseEntity<DadosDetalhamentoUva> cadastrar(@RequestBody @Valid DadosCadastroUva dados, UriComponentsBuilder builder){ //DadosCadastroRemedio é um DTO construido nu
        var uva = repository.save(new Uva(dados)); // função do proprio jpa
        // o DTO passado como argumento é lido no construtor, que retorna os atributos
        var uri = builder.path("/remessaUva/{id}").buildAndExpand(uva.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DadosDetalhamentoUva(uva));
    }

    @GetMapping()
    public ResponseEntity<List<DadosListagemUva>> listar(){
        var lista = repository.findAllByValidTrue().stream().map(DadosListagemUva::new).toList(); //.stream().map(DadosListagemRemessaUva::new).toList(); //função do proprio jpa
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUva> detalharUva(@PathVariable Long id){
        var uva = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoUva(uva));
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody DadosAtualizarUva dados){
        var uva = repository.getReferenceById(dados.id());
        uva.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUva(uva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<?> inativar(@PathVariable Long id){
        var uva = repository.getReferenceById(id);
        uva.inativar();
        return ResponseEntity.noContent().build();
    }
}