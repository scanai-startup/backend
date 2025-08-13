package com.scanai.api.controllers;

import com.scanai.api.domain.analiseprefermentacao.Analiseprefermentacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosCadastroAnalisePreFermetacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosDetalhamentoAnalisePreFermentacao;
import com.scanai.api.repositories.AnalisePreFermentacaoRepository;
import com.scanai.api.services.AnalisePreFermentacaoServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analiseprefermentacao")
public class AnalisePreFermentacaoController {

    @Autowired
    private AnalisePreFermentacaoServiceInterface service;

    @PostMapping("/register")
    public DadosDetalhamentoAnalisePreFermentacao register(@RequestBody @Valid DadosCadastroAnalisePreFermetacao data){
        return service.register(data);
    }
}
