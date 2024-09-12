package com.scanai.api.controllers;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;
import com.scanai.api.domain.analisepedecuba.dto.RegisterAnalisepedecubaDTO;
import com.scanai.api.domain.analisepedecuba.dto.ReqListAnalisespedecubaDTO;
import com.scanai.api.domain.analisepedecuba.dto.ResListAnalisespedecubaDTO;
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
    public ResponseEntity regiterAnalisepedecuba(@RequestBody @Valid RegisterAnalisepedecubaDTO data, UriComponentsBuilder uriBuilder){
        Analisepedecuba newAnalisepedecuba = service.createAnalisespedecuba(data);
        repository.save(newAnalisepedecuba);
        var uri = uriBuilder.path("analisepedecuba/register/{id}").buildAndExpand(newAnalisepedecuba.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/listAnalises")
    public ResponseEntity<List<ResListAnalisespedecubaDTO>> listAnalisespedecuba(@RequestBody @Valid ReqListAnalisespedecubaDTO data){
        var lista = repository.findAllByFkpedecuba(data.fkpedecuba()).stream().map(ResListAnalisespedecubaDTO::new).toList();
        return ResponseEntity.ok(lista);
    }
}
