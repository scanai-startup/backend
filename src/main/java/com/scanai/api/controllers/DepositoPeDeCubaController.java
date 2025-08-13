package com.scanai.api.controllers;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosCadastroDepositoPeDeCuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosDetalhamentoDepositoPeDeCuba;
import com.scanai.api.repositories.DepositoPeDeCubaRepository;
import com.scanai.api.services.DepositoPeDeCubaServiceInterface;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/depositoPeDeCuba")
public class DepositoPeDeCubaController {

    @Autowired
    private DepositoPeDeCubaServiceInterface service;


    @PostMapping("/register")
    @Transactional
    public DadosDetalhamentoDepositoPeDeCuba register(@RequestBody @Valid DadosCadastroDepositoPeDeCuba data){
        return service.register(data);
    }
}
