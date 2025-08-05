package com.scanai.api.controllers;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosCadastroDepositoPeDeCuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosDetalhamentoDepositoPeDeCuba;
import com.scanai.api.repositories.DepositoPedecubaRepository;
import com.scanai.api.services.DepositoPeDeCubaServiceInterface;
import com.scanai.api.services.implement.DepositoPeDeCubaService;
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
@RequestMapping("/depositoPeDeCuba")
public class DepositoPeDeCubaController {

    @Autowired
    private DepositoPeDeCubaServiceInterface service;

    @Autowired
    private DepositoPedecubaRepository repository;

    @PostMapping("/register")
    @Transactional
    public DadosDetalhamentoDepositoPeDeCuba register(@RequestBody @Valid DadosCadastroDepositoPeDeCuba data){
        Depositopedecuba newDepositopedecuba = service.register(data);
        return new DadosDetalhamentoDepositoPeDeCuba(newDepositopedecuba);
    }
}
