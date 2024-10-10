package com.scanai.api.controllers;

import com.scanai.api.domain.rotulo.DTO.DadosCadastroRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosDetalhamentoRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosAtualizarRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosListagemRotulo;
import com.scanai.api.services.RotuloService;
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
    private RotuloService rotuloService;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoRotulo> register(@RequestBody @Valid DadosCadastroRotulo dados, UriComponentsBuilder builder){ //DadosCadastroRemedio é um DTO construido nu
        var rotulo = rotuloService.register(dados);
        var uri = builder.path("/rotulo/{id}").buildAndExpand(rotulo.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DadosDetalhamentoRotulo(rotulo));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DadosListagemRotulo>> getAll(){
        var lista = rotuloService.listAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/getElement/{id}")
    public ResponseEntity<DadosDetalhamentoRotulo> getElement(@PathVariable Long id){
        var rotulo = rotuloService.getElement(id);
        return ResponseEntity.ok(new DadosDetalhamentoRotulo(rotulo));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DadosDetalhamentoRotulo> update(@RequestBody DadosAtualizarRotulo dados){
        var dadosDetalhamentoRotulo = rotuloService.update(dados);
        return ResponseEntity.ok(dadosDetalhamentoRotulo);
    }

    @Transactional
    @DeleteMapping("hardDelete/{id}")
    public ResponseEntity<?> hardDelete(@PathVariable Long id){
        rotuloService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/softDelete/{id}")
    public ResponseEntity<?> softDelete(@PathVariable Long id){
        rotuloService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

}
