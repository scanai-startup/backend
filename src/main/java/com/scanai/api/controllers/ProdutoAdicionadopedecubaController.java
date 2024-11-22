package com.scanai.api.controllers;

import com.scanai.api.domain.produtoadcpedecuba.ProdutoAdicionadopedecuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosDetalhamentoProdutoAdicionadoPeDeCuba;
import com.scanai.api.repositories.ProdutoAdicionadopedecubaRepository;
import com.scanai.api.services.ProdutoAdicionadopedecubaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produtoadcpedecuba")
public class ProdutoAdicionadopedecubaController {

    @Autowired
    ProdutoAdicionadopedecubaService service;

    @Autowired
    ProdutoAdicionadopedecubaRepository repository;

    @PostMapping("/register")
    public ResponseEntity<List<ProdutoAdicionadopedecuba>> register(@RequestBody DadosCadastroProdutoAdicionadoPeDeCuba dados) {
        List<ProdutoAdicionadopedecuba> produtos = service.register(dados);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/getAllByPeDeCubaId/{fkPeDeCuba}")
    public ResponseEntity<List<DadosDetalhamentoProdutoAdicionadoPeDeCuba>> getAllByPeDeCubaId(@PathVariable Long fkPeDeCuba) {
        List<DadosDetalhamentoProdutoAdicionadoPeDeCuba> lista = service.getAllByPeDeCubaId(fkPeDeCuba);
        return ResponseEntity.ok(lista);
    }
}
