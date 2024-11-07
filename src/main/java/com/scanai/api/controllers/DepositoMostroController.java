package com.scanai.api.controllers;

import com.scanai.api.domain.depositomostro.DepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosCadastroDepositoMostro;
import com.scanai.api.domain.depositomostro.dto.DadosDetalhamentoDepositoMostro;
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
    private DepositoMostroRepository depositoMostroRepository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DadosDetalhamentoDepositoMostro> register(@RequestBody @Valid DadosCadastroDepositoMostro data, UriComponentsBuilder uriBuilder){
        DepositoMostro newDepositoMostro = service.register(data);
        var uri = uriBuilder.path("depositomostro/register/{id}").buildAndExpand(newDepositoMostro.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoDepositoMostro(newDepositoMostro));
    }
}
