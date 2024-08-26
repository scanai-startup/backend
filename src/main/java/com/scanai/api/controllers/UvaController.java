package com.scanai.api.controllers;

import com.scanai.api.domain.uva.dto.DadosAtualizarUva;
import com.scanai.api.domain.uva.dto.DadosCadastroUva;
import com.scanai.api.domain.uva.dto.DadosDetalhamentoUva;
import com.scanai.api.domain.uva.dto.DadosListagemUva;
import com.scanai.api.services.UvaService;
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
    private UvaService uvaService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DadosDetalhamentoUva> register(@RequestBody @Valid DadosCadastroUva dados, UriComponentsBuilder builder){ //DadosCadastroRemedio é um DTO construido nu
        var uva = uvaService.register(dados); // função do proprio jpa
        // o DTO passado como argumento é lido no construtor, que retorna os atributos
        var uri = builder.path("/remessaUva/{id}").buildAndExpand(uva.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DadosDetalhamentoUva(uva));
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<DadosListagemUva>> listAll(){
        var lista = uvaService.listAll(); //.stream().map(DadosListagemRemessaUva::new).toList(); //função do proprio jpa
        return ResponseEntity.ok(lista);
    }

    @GetMapping("getElement/{id}")
    public ResponseEntity<DadosDetalhamentoUva> getElement(@PathVariable Long id){
        var uva = uvaService.getElement(id);
        return ResponseEntity.ok(new DadosDetalhamentoUva(uva));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestBody DadosAtualizarUva dados){
        var uva = uvaService.update(dados);
        return ResponseEntity.ok(uva);
    }

    @DeleteMapping("hardDelete/{id}")
    public ResponseEntity<?> hardDelete(@PathVariable Long id){
        uvaService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/softDelete/{id}")
    @Transactional
    public ResponseEntity<?> softDelete(@PathVariable Long id){
        uvaService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}