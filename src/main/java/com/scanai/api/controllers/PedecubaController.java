package com.scanai.api.controllers;

import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;
import com.scanai.api.domain.pedecuba.dto.DadosDetalhamentoPeDeCuba;
import com.scanai.api.repositories.PedecubaRepository;
import com.scanai.api.services.PedecubaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pedecuba")
public class PedecubaController {

    @Autowired
    private PedecubaRepository repository;

    @Autowired
    private PedecubaService service;

    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoPeDeCuba> register(@RequestBody @Valid DadosCadastroPeDeCuba data, UriComponentsBuilder uriBuilder){
        Pedecuba newPedecuba = service.register(data);
        var uri = uriBuilder.path("pedecuba/register/{id}").buildAndExpand(newPedecuba.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPeDeCuba(newPedecuba));
    }

    @GetMapping("/getALl")
    public ResponseEntity<List<Pedecuba>> getAll(){
        return ResponseEntity.ok().body(repository.findAllByValidTrue());
    }

    @PutMapping("/softDelete/{id}")
    @Transactional
    public ResponseEntity<?> softDelete(@PathVariable Long id){
        service.softDelete(repository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/activate/{id}")
    @Transactional
    public ResponseEntity<?> activate(@PathVariable Long id){
        service.activate(repository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

}

