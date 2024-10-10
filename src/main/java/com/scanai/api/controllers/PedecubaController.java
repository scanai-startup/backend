package com.scanai.api.controllers;

import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;
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
    public ResponseEntity regiterPedecuba(@RequestBody @Valid DadosCadastroPeDeCuba data, UriComponentsBuilder uriBuilder){
        Pedecuba newPedecuba = service.createPedecuba(data);
        repository.save(newPedecuba);
        var uri = uriBuilder.path("pedecuba/register/{id}").buildAndExpand(newPedecuba.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/listPedecubas")
    public ResponseEntity<List<Pedecuba>> listPedecuba(){
        return ResponseEntity.ok().body(repository.findAllByValidTrue());
    }

    @PutMapping("/invalidate/{id}")
    @Transactional
    public ResponseEntity invalidatePedecuba(@PathVariable Long id){
        service.invalidadePedecuba(repository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/validate/{id}")
    @Transactional
    public ResponseEntity validatePedecuba(@PathVariable Long id){
        service.validadePedecuba(repository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

}

