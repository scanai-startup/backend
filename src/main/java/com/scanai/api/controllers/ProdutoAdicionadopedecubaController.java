package com.scanai.api.controllers;

import com.scanai.api.domain.produtoadcpedecuba.ProdutoAdicionadopedecuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosDetalhamentoProdutoAdicionadoPeDeCuba;
import com.scanai.api.repositories.ProdutoAdicionadopedecubaRepository;
import com.scanai.api.services.ProdutoAdicionadopedecubaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtoadcpedecuba")
public class ProdutoAdicionadopedecubaController {

    @Autowired
    ProdutoAdicionadopedecubaService service;

    @Autowired
    ProdutoAdicionadopedecubaRepository repository;

    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoProdutoAdicionadoPeDeCuba> register(@RequestBody @Valid DadosCadastroProdutoAdicionadoPeDeCuba data, UriComponentsBuilder uriBuilder){
        ProdutoAdicionadopedecuba newProdutoadcpedecuba = service.register(data);
        var uri = uriBuilder.path("produtoadcpedecuba/register/{id}").buildAndExpand(newProdutoadcpedecuba.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoProdutoAdicionadoPeDeCuba(newProdutoadcpedecuba));
    }
}
