package com.scanai.api.controllers;

import com.scanai.api.domain.vinho.DTO.DadosAtualizarVinho;
import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import com.scanai.api.domain.vinho.DTO.DadosDetalhamentoVinho;
import com.scanai.api.domain.vinho.DTO.DadosListagemVinho;
import com.scanai.api.services.VinhoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/vinho")

public class VinhoController {
    VinhoService vinhoService;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoVinho> register(@RequestBody @Valid DadosCadastroVinho dados, UriComponentsBuilder builder){ //DadosCadastroRemedio é um DTO construido nu

        var vinho = vinhoService.register(dados);// função do proprio jpa
        // o DTO passado como argumento é lido no construtor, que retorna os atributos
        var uri = builder.path("/vinho/{id}").buildAndExpand(vinho.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DadosDetalhamentoVinho(vinho));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DadosListagemVinho>> getAll(){
        var lista = vinhoService.getAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/getElement/{id}")
    public ResponseEntity<DadosDetalhamentoVinho> getElement(@PathVariable Long id){
        var vinho = vinhoService.getElement(id);
        return ResponseEntity.ok(new DadosDetalhamentoVinho(vinho));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DadosDetalhamentoVinho> update(@RequestBody DadosAtualizarVinho dados){
        var vinho = vinhoService.update(dados);
        return ResponseEntity.ok(vinho);
    }

    @DeleteMapping("hardDelete/{id}")
    @Transactional
    public ResponseEntity<?> hardDelete(@PathVariable Long id){
        vinhoService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/softDelete/{id}")
    @Transactional
    public ResponseEntity<?> softDelete(@PathVariable Long id){
        vinhoService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/activate/{id}")
    @Transactional
    public ResponseEntity<?> activate(@PathVariable Long id){
        vinhoService.activate(id);
        return ResponseEntity.noContent().build();
    }
}
