package com.scanai.api.controllers;

import com.scanai.api.domain.depositomostro.DepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosCadastroDepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosDetalhamentoDepositoMostro;
import com.scanai.api.repositories.DepositoMostroRepository;
import com.scanai.api.services.DepositoMostroServiceInterface;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/depositoMostro")
public class DepositoMostroController {

    @Autowired
    private DepositoMostroServiceInterface service;

    @Autowired
    private DepositoMostroRepository depositoMostroRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public DadosDetalhamentoDepositoMostro register(@RequestBody @Valid DadosCadastroDepositoMostro data){
        DepositoMostro newDepositoMostro = service.register(data);
        return new DadosDetalhamentoDepositoMostro(newDepositoMostro);
    }
}
