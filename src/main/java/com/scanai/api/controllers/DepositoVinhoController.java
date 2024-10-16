package com.scanai.api.controllers;

import com.scanai.api.domain.depositovinho.Depositovinho;
import com.scanai.api.domain.depositovinho.dto.DadosCadastroDepositoVinho;
import com.scanai.api.domain.depositovinho.dto.DadosDetalhamentoDepositoVinho;
import com.scanai.api.repositories.DepositoVinhoRepository;
import com.scanai.api.services.DepositoVinhoService;
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
@RequestMapping("/depositovinho")
public class DepositoVinhoController {

    @Autowired
    private DepositoVinhoService service;

    @Autowired
    private DepositoVinhoRepository repository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DadosDetalhamentoDepositoVinho> register(@RequestBody @Valid DadosCadastroDepositoVinho data, UriComponentsBuilder uriBuilder){
        Depositovinho newDepositovinho = service.register(data);
        var uri = uriBuilder.path("depositovinho/register/{id}").buildAndExpand(newDepositovinho.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoDepositoVinho(newDepositovinho));
    }
}
