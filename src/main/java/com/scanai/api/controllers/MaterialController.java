package com.scanai.api.controllers;

import com.scanai.api.domain.material.Material;
import com.scanai.api.domain.material.dto.DadosCadastroMaterial;
import com.scanai.api.domain.material.dto.DadosDetalhamentoMaterial;
import com.scanai.api.domain.material.dto.DadosListagemMaterial;
import com.scanai.api.domain.mostro.dto.DadosListagemMostro;
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
    public ResponseEntity<DadosDetalhamentoMaterial> register(@RequestBody @Valid DadosCadastroMaterial data, UriComponentsBuilder uriBuilder){
        Material newMaterial = service.register(data);
        var uri = uriBuilder.path("material/register/{id}").buildAndExpand(newMaterial.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMaterial(newMaterial));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DadosListagemMaterial>> list(){
        return ResponseEntity.ok().body(service.getAll());
    }


}
