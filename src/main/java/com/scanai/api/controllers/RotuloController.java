package com.scanai.api.controllers;

import com.scanai.api.domain.rotulo.DTO.DadosCadastroRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosDetalhamentoRotulo;
import com.scanai.api.repositories.RotuloRepository;
import com.scanai.api.domain.rotulo.DTO.DadosAtualizarRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosListagemRotulo;
import com.scanai.api.domain.rotulo.Rotulo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/rotulo")

public class RotuloController {
    @Autowired
    private RotuloRepository repository;

    @Transactional
    @PostMapping()
    public ResponseEntity<DadosDetalhamentoRotulo> cadastrarRotulo(@RequestBody @Valid DadosCadastroRotulo dados, UriComponentsBuilder builder){ //DadosCadastroRemedio é um DTO construido nu
        var rotulo = repository.save(new Rotulo(dados)); // função do proprio jpa
        // o DTO passado como argumento é lido no construtor, que retorna os atributos
        var uri = builder.path("/rotulo/{id}").buildAndExpand(rotulo.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DadosDetalhamentoRotulo(rotulo));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DadosListagemRotulo>> listarRotulo(){
        var lista = repository.findAllByValidTrue().stream().map(DadosListagemRotulo::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity<DadosDetalhamentoRotulo> detalharRotulo(@PathVariable Long id){
        var rotulo = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoRotulo(rotulo));
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<DadosDetalhamentoRotulo> atualizarRotulo(@RequestBody DadosAtualizarRotulo dados){
        var rotulo = repository.getReferenceById(dados.id());
        rotulo.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoRotulo(rotulo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarRotulo(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inativar/{id}")
    public ResponseEntity<?> inativarRotulo(@PathVariable Long id){
        var rotulo = repository.getReferenceById(id);
        rotulo.inativar();
        return ResponseEntity.noContent().build();
    }

}
