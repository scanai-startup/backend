package com.scanai.api.controllers;

import com.scanai.api.domain.vinho.DTO.DadosAtualizarVinho;
import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import com.scanai.api.domain.vinho.DTO.DadosDetalhamentoVinho;
import com.scanai.api.domain.vinho.DTO.DadosListagemVinho;
import com.scanai.api.domain.vinho.Vinho;
import com.scanai.api.repositories.VinhoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/vinho")

public class VinhoController {
    @Autowired
    private VinhoRepository repository;

    @Transactional
    @PostMapping()
    public ResponseEntity<DadosDetalhamentoVinho> cadastrarVinho(@RequestBody @Valid DadosCadastroVinho dados, UriComponentsBuilder builder){ //DadosCadastroRemedio é um DTO construido nu
        System.out.println(dados);

        var vinho = repository.save(new Vinho(dados)); // função do proprio jpa
        // o DTO passado como argumento é lido no construtor, que retorna os atributos
        var uri = builder.path("/vinho/{id}").buildAndExpand(vinho.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DadosDetalhamentoVinho(vinho));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DadosListagemVinho>> listarVinho(){
        var lista = repository.findAllByValidTrue().stream().map(DadosListagemVinho::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity<DadosDetalhamentoVinho> detalharVinho(@PathVariable Long id){
        var vinho = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoVinho(vinho));
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<DadosDetalhamentoVinho> atualizarVinho(@RequestBody DadosAtualizarVinho dados){
        var vinho = repository.getReferenceById(dados.id());
        vinho.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoVinho(vinho));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVinho(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inativar/{id}")
    public ResponseEntity<?> inativarVinho(@PathVariable Long id){
        var vinho = repository.getReferenceById(id);
        vinho.inativar();
        return ResponseEntity.noContent().build();
    }
}
