package com.scanai.api.controllers;

import com.scanai.api.domain.depositomostro.Depositomostro;
import com.scanai.api.domain.depositomostro.dto.RegisterDepositomostroDTO;
import com.scanai.api.repositories.DepositoMostroRepository;
import com.scanai.api.services.DepositoMostroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/depositomostro")
public class DepositoMostroController {

    @Autowired
    private DepositoMostroService service;

    @Autowired
    private DepositoMostroRepository repository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity newDepositomostro(@RequestBody @Valid RegisterDepositomostroDTO data, UriComponentsBuilder uriBuilder){
        Depositomostro newDepositomostro = service.createDepositomostro(data);
        repository.save(newDepositomostro);
        var uri = uriBuilder.path("depositomostro/register/{id}").buildAndExpand(newDepositomostro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}