package com.scanai.api.controllers;

import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.repositories.MostroRepository;
import com.scanai.api.services.MostroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/mostro")
public class MostroController {

    @Autowired
    private MostroRepository repository;

    @Autowired
    private MostroService service;

    @PostMapping("/register")
    public ResponseEntity regiterMostro(@RequestBody @Valid DadosCadastroMostro data, UriComponentsBuilder uriBuilder){
        Mostro newMostro = service.createMostro(data);
        repository.save(newMostro);
        var uri = uriBuilder.path("mostro/register/{id}").buildAndExpand(newMostro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Mostro>> listMostros(){
        return ResponseEntity.ok().body(repository.findAllByValidTrue());
    }

    @PutMapping("/softDelete/{id}")
    @Transactional
    public ResponseEntity invalidateMostro(@PathVariable Long id){
        service.invalidadeMostro(repository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/activate/{id}")
    @Transactional
    public ResponseEntity validateMostro(@PathVariable Long id){
        service.invalidadeMostro(repository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

}

