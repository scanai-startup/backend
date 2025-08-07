package com.scanai.api.controllers;

import com.scanai.api.domain.rotulagem.dto.DadosCadastroRotulagem;
import com.scanai.api.domain.rotulagem.dto.DadosDetalhamentoRotulagem;
import com.scanai.api.services.RotulagemServiceInterface;
import com.scanai.api.services.implement.RotulagemService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/rotulagem")
public class RotulagemController {

    @Autowired
    private RotulagemServiceInterface service;

    @Transactional
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DadosDetalhamentoRotulagem register(@RequestBody @Valid DadosCadastroRotulagem dados){
        var newRotulagem = service.register(dados);
        return new DadosDetalhamentoRotulagem(newRotulagem);
    }
}
