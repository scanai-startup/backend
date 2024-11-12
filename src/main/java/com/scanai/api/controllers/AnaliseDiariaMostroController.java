package com.scanai.api.controllers;

import com.scanai.api.domain.analisediariamostro.dto.DadosAtualizarAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosCadastroAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosDetalhamentoAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosListagemAnaliseDiariaMostro;
import com.scanai.api.services.AnaliseDiariaMostroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/analisediariamostro")

public class AnaliseDiariaMostroController {
    @Autowired
    AnaliseDiariaMostroService analiseDiariaMostroService;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoAnaliseDiariaMostro> register(@RequestBody @Valid DadosCadastroAnaliseDiariaMostro dados, UriComponentsBuilder builder){

        var analiseDiariaMostro = analiseDiariaMostroService.register(dados);
        var uri = builder.path("/analisediariamostro/{id}").buildAndExpand(analiseDiariaMostro.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DadosDetalhamentoAnaliseDiariaMostro(analiseDiariaMostro));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DadosListagemAnaliseDiariaMostro>> getAll(){
        var lista = analiseDiariaMostroService.getAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/getElement/{id}")
    public ResponseEntity<DadosDetalhamentoAnaliseDiariaMostro> getElement(@PathVariable Long id){
        var analiseDiariaMostro = analiseDiariaMostroService.getElement(id);
        return ResponseEntity.ok(new DadosDetalhamentoAnaliseDiariaMostro(analiseDiariaMostro));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DadosDetalhamentoAnaliseDiariaMostro> update(@RequestBody DadosAtualizarAnaliseDiariaMostro dados){
        var analiseDiariaMostro = analiseDiariaMostroService.update(dados);
        return ResponseEntity.ok(analiseDiariaMostro);
    }

    @DeleteMapping("hardDelete/{id}")
    @Transactional
    public ResponseEntity<?> hardDelete(@PathVariable Long id){
        analiseDiariaMostroService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

}
