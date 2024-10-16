package com.scanai.api.controllers;

import com.scanai.api.domain.analiseprefermentacao.Analiseprefermentacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosCadastroAnalisePreFermetacao;
import com.scanai.api.domain.analiseprefermentacao.dto.DadosDetalhamentoAnalisePreFermentacao;
import com.scanai.api.repositories.AnaliseprefermentacaoRepository;
import com.scanai.api.services.AnaliseprefermentacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/analiseprefermentacao")
public class AnalisePrefermentacaoController {

    @Autowired
    private AnaliseprefermentacaoRepository repository;

    @Autowired
    private AnaliseprefermentacaoService service;

    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoAnalisePreFermentacao> regiterAnaliseprefermentacao(@RequestBody @Valid DadosCadastroAnalisePreFermetacao data, UriComponentsBuilder uriBuilder){
        Analiseprefermentacao newAnaliseprefermentacao = service.register(data);
        var uri = uriBuilder.path("analiseprefermentacao/register/{id}").buildAndExpand(newAnaliseprefermentacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAnalisePreFermentacao(newAnaliseprefermentacao));
    }
}
