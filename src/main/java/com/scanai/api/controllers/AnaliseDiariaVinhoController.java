package com.scanai.api.controllers;

import com.scanai.api.domain.analisediariavinho.dto.DadosAtualizarAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosCadastroAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosDetalhamentoAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosListagemAnaliseDiariaVinho;
import com.scanai.api.services.AnaliseDiariaVinhoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/analisediariavinho")

public class AnaliseDiariaVinhoController {
    AnaliseDiariaVinhoService analiseDiariaVinhoService;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoAnaliseDiariaVinho> register(@RequestBody @Valid DadosCadastroAnaliseDiariaVinho dados, UriComponentsBuilder builder){

        var analiseDiariaVinho = analiseDiariaVinhoService.register(dados);
        var uri = builder.path("/analisediariavinho/{id}").buildAndExpand(analiseDiariaVinho.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DadosDetalhamentoAnaliseDiariaVinho(analiseDiariaVinho));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DadosListagemAnaliseDiariaVinho>> getAll(){
        var lista = analiseDiariaVinhoService.getAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/getElement/{id}")
    public ResponseEntity<DadosDetalhamentoAnaliseDiariaVinho> getElement(@PathVariable Long id){
        var analiseDiariaVinho = analiseDiariaVinhoService.getElement(id);
        return ResponseEntity.ok(new DadosDetalhamentoAnaliseDiariaVinho(analiseDiariaVinho));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DadosDetalhamentoAnaliseDiariaVinho> update(@RequestBody DadosAtualizarAnaliseDiariaVinho dados){
        var analiseDiariaVinho = analiseDiariaVinhoService.update(dados);
        return ResponseEntity.ok(analiseDiariaVinho);
    }

    @DeleteMapping("hardDelete/{id}")
    @Transactional
    public ResponseEntity<?> hardDelete(@PathVariable Long id){
        analiseDiariaVinhoService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

}
