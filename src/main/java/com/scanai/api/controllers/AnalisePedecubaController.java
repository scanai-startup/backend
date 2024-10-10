package com.scanai.api.controllers;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosCadastroAnalisePeDeCuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosListagemAnalisesPeDeCuba;
import com.scanai.api.repositories.AnalisepedecubaRepository;
import com.scanai.api.services.AnalisepedecubaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/analisepedecuba")
public class AnalisePedecubaController {

    @Autowired
    private AnalisepedecubaRepository repository;

    @Autowired
    private AnalisepedecubaService service;

    @PostMapping("/register")
    public ResponseEntity regiterAnalisepedecuba(@RequestBody @Valid DadosCadastroAnalisePeDeCuba data, UriComponentsBuilder uriBuilder){
        Analisepedecuba newAnalisepedecuba = service.createAnalisespedecuba(data);
        repository.save(newAnalisepedecuba);
        var uri = uriBuilder.path("analisepedecuba/register/{id}").buildAndExpand(newAnalisepedecuba.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/listAnalises/{id}")
    public ResponseEntity<List<DadosListagemAnalisesPeDeCuba>> listAnalisespedecuba(@PathVariable Long id){
        var lista = repository.findAllByFkpedecuba(id).stream().map(DadosListagemAnalisesPeDeCuba::new).toList();
        return ResponseEntity.ok(lista);
    }
}
