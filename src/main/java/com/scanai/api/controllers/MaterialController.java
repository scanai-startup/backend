package com.scanai.api.controllers;

import com.scanai.api.domain.material.Material;
import com.scanai.api.domain.material.dto.DadosCadastroMaterial;
import com.scanai.api.repositories.MaterialRepository;
import com.scanai.api.services.MaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialRepository repository;

    @Autowired
    private MaterialService service;

    @PostMapping("/register")
    public ResponseEntity regiterMaterial(@RequestBody @Valid DadosCadastroMaterial data, UriComponentsBuilder uriBuilder){
        Material newMaterial = service.register(data);
        repository.save(newMaterial);
        var uri = uriBuilder.path("material/register/{id}").buildAndExpand(newMaterial.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Material>> listMateriais(){
        return ResponseEntity.ok().body(repository.findAll());
    }


}
