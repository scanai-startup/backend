package com.scanai.api.controllers;

import com.scanai.api.domain.mostrovinho.dto.DadosAtualizarMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosCadastroMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosDetalhamentoMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosListagemMostroVinho;
import com.scanai.api.services.MostroVinhoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/mostrovinho")
public class MostroVinhoController {
    @Autowired
    MostroVinhoService mostroVinhoService;
    public ResponseEntity<DadosDetalhamentoMostroVinho> register(@RequestBody @Valid DadosCadastroMostroVinho dados, UriComponentsBuilder builder){ //DadosCadastroRemedio é um DTO construido nu

        var mostroVinho = mostroVinhoService.register(dados);// função do proprio jpa
        // o DTO passado como argumento é lido no construtor, que retorna os atributos
        var uri = builder.path("/mostroVinho/{id}").buildAndExpand(mostroVinho.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DadosDetalhamentoMostroVinho(mostroVinho));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DadosListagemMostroVinho>> getAll(){
        var lista = mostroVinhoService.getAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/getElement/{id}")
    public ResponseEntity<DadosDetalhamentoMostroVinho> getElement(@PathVariable Long id){
        var mostroVinho = mostroVinhoService.getElement(id);
        return ResponseEntity.ok(new DadosDetalhamentoMostroVinho(mostroVinho));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DadosDetalhamentoMostroVinho> update(@RequestBody DadosAtualizarMostroVinho dados){
        var mostroVinho = mostroVinhoService.update(dados);
        return ResponseEntity.ok(mostroVinho);
    }

    @DeleteMapping("hardDelete/{id}")
    @Transactional
    public ResponseEntity<?> hardDelete(@PathVariable Long id){
        mostroVinhoService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/softDelete/{id}")
    @Transactional
    public ResponseEntity<?> softDelete(@PathVariable Long id){
        mostroVinhoService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
