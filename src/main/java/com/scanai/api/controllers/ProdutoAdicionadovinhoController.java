package com.scanai.api.controllers;

import com.scanai.api.domain.produtoadcvinho.ProdutoAdicionadovinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosCadastroProdutoAdicionadoVinho;
import com.scanai.api.repositories.ProdutoAdicionadovinhoRepository;
import com.scanai.api.services.ProdutoAdicionadovinhoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtoadcvinho")
public class ProdutoAdicionadovinhoController {

    @Autowired
    ProdutoAdicionadovinhoService service;

    @Autowired
    ProdutoAdicionadovinhoRepository repository;

    @PostMapping("/register")
    public ResponseEntity newProdutoadcvinho(@RequestBody @Valid DadosCadastroProdutoAdicionadoVinho data, UriComponentsBuilder uriBuilder){
        ProdutoAdicionadovinho newProdutoadcvinho = service.register(data);
        repository.save(newProdutoadcvinho);
        var uri = uriBuilder.path("produtoadcvinho/register/{id}").buildAndExpand(newProdutoadcvinho.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
