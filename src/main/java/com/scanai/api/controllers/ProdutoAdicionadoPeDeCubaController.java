package com.scanai.api.controllers;

import com.scanai.api.domain.produtoadcpedecuba.ProdutoAdicionadopedecuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosAtualizarProdutoAdicionadoPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosDetalhamentoProdutoAdicionadoPeDeCuba;
import com.scanai.api.repositories.ProdutoAdicionadopedecubaRepository;
import com.scanai.api.services.ProdutoAdicionadoPeDeCubaServiceInterface;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtoAdcPeDeCuba")
public class ProdutoAdicionadoPeDeCubaController {

    @Autowired
    ProdutoAdicionadoPeDeCubaServiceInterface service;

    @Autowired
    ProdutoAdicionadopedecubaRepository repository;

    @Transactional
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProdutoAdicionadopedecuba> register(@RequestBody DadosCadastroProdutoAdicionadoPeDeCuba dados) {
        return service.register(dados);
    }

    @GetMapping("/getAllByPeDeCubaId/{fkPeDeCuba}")
    public List<DadosDetalhamentoProdutoAdicionadoPeDeCuba> getAllByPeDeCubaId(@PathVariable Long fkPeDeCuba) {
        return service.getAllByPeDeCubaId(fkPeDeCuba);
    }

    @Transactional
    @PutMapping("/update")
    public DadosDetalhamentoProdutoAdicionadoPeDeCuba update(@RequestBody @Valid DadosAtualizarProdutoAdicionadoPeDeCuba data){
        return service.update(data);
    }

    @DeleteMapping("/hardDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hardDelete(@PathVariable Long id){
        service.hardDelete(id);
    }
}
