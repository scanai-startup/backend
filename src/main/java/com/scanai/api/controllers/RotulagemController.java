package com.scanai.api.controllers;

import com.scanai.api.domain.rotulagem.dto.DadosCadastroRotulagem;
import com.scanai.api.domain.rotulagem.dto.DadosDetalhamentoRotulagem;
import com.scanai.api.services.RotulagemService;
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
@RequestMapping("/rotulagem")
public class RotulagemController {

    @Autowired
    private RotulagemService service;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoRotulagem> register(@RequestBody @Valid DadosCadastroRotulagem dados, UriComponentsBuilder builder){
        var newRotulagem = service.register(dados);
        var uri = builder.path("/rotulagem/{id}").buildAndExpand(newRotulagem.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DadosDetalhamentoRotulagem(newRotulagem));
    }
}
