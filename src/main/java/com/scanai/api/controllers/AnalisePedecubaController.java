package com.scanai.api.controllers;

import com.scanai.api.domain.analisespedecuba.Analisespedecuba;
import com.scanai.api.domain.analisespedecuba.dto.RegisterAnalisepedecubaDTO;
import com.scanai.api.domain.analisespedecuba.dto.ReqListAnalisespedecubaDTO;
import com.scanai.api.domain.analisespedecuba.dto.ResListAnalisespedecubaDTO;
import com.scanai.api.domain.higienedeposito.dto.ReqListHigienedepositoDTO;
import com.scanai.api.domain.higienedeposito.dto.ResListHigienedeposito;
import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.RegisterMostroDTO;
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
        Analisespedecuba newAnalisepedecuba = service.createAnalisespedecuba(data);
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
