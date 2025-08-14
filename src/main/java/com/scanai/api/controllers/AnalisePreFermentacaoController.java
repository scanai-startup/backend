package com.scanai.api.controllers;

import com.scanai.api.domain.analiseprefermentacao.Analiseprefermentacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosCadastroAnalisePreFermetacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosDetalhamentoAnalisePreFermentacao;
import com.scanai.api.repositories.AnalisePreFermentacaoRepository;
import com.scanai.api.services.AnalisePreFermentacaoServiceInterface;
import com.scanai.api.services.implement.AnalisePreFermentacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/analiseprefermentacao")
public class AnalisePreFermentacaoController {

    @Autowired
    private AnalisePreFermentacaoServiceInterface service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public DadosDetalhamentoAnalisePreFermentacao register(@RequestBody @Valid DadosCadastroAnalisePreFermetacao data){
        return service.register(data);
    }
}
