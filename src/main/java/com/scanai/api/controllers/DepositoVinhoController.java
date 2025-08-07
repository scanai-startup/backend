package com.scanai.api.controllers;

import com.scanai.api.domain.depositovinho.Depositovinho;
import com.scanai.api.domain.depositovinho.dto.DadosCadastroDepositoVinho;
import com.scanai.api.domain.depositovinho.dto.DadosDetalhamentoDepositoVinho;
import com.scanai.api.repositories.DepositoVinhoRepository;
import com.scanai.api.services.implement.DepositoVinhoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/depositoVinho")
public class DepositoVinhoController {

    @Autowired
    private DepositoVinhoService service;

    @Autowired
    private DepositoVinhoRepository repository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public DadosDetalhamentoDepositoVinho register(@RequestBody @Valid DadosCadastroDepositoVinho data){
        Depositovinho newDepositovinho = service.register(data);
        return new DadosDetalhamentoDepositoVinho(newDepositovinho);
    }
}
