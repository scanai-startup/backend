package com.scanai.api.controllers;


import com.scanai.api.domain.viticultor.DTO.DadosAtualizarViticultor;
import com.scanai.api.domain.viticultor.DTO.DadosCadastroViticultor;
import com.scanai.api.domain.viticultor.DTO.DadosDetalhamentoViticultor;
import com.scanai.api.domain.viticultor.DTO.DadosListagemViticultor;
import com.scanai.api.services.ViticultorService;
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
    private ViticultorService viticultorService;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoViticultor> register(@RequestBody @Valid DadosCadastroViticultor dados, UriComponentsBuilder builder){ //DadosCadastroRemedio é um DTO construido nu

        var viticultor = viticultorService.register(dados); // função do proprio jpa
        // o DTO passado como argumento é lido no construtor, que retorna os atributos
        var uri = builder.path("/viticultor/{id}").buildAndExpand(viticultor.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DadosDetalhamentoViticultor(viticultor));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DadosListagemViticultor>> getAll(){
        var lista = viticultorService.listAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/getElement/{id}")
    public ResponseEntity<DadosDetalhamentoViticultor> getElement(@PathVariable Long id){
        var viticultor = viticultorService.getElement(id);
        return ResponseEntity.ok(new DadosDetalhamentoViticultor(viticultor));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DadosDetalhamentoViticultor> update(@RequestBody DadosAtualizarViticultor dados){
        var viticultor = viticultorService.update(dados);
        return ResponseEntity.ok(viticultor);
    }

    @DeleteMapping("hardDelete/{id}")
    public ResponseEntity<?> hardDelete(@PathVariable Long id){
        viticultorService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

}
