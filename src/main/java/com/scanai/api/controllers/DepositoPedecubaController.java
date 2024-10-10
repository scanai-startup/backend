package com.scanai.api.controllers;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosCadastroDepositoPeDeCuba;
import com.scanai.api.repositories.DepositoPedecubaRepository;
import com.scanai.api.services.DepositoPedecubaService;
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
@RequestMapping("/depositopedecuba")
public class DepositoPedecubaController {

    @Autowired
    private DepositoPedecubaService service;

    @Autowired
    private DepositoPedecubaRepository repository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity newDepositopedecuba(@RequestBody @Valid DadosCadastroDepositoPeDeCuba data, UriComponentsBuilder uriBuilder){
        Depositopedecuba newDepositopedecuba = service.register(data);
        repository.save(newDepositopedecuba);
        var uri = uriBuilder.path("depositopedecuba/register/{id}").buildAndExpand(newDepositopedecuba.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
