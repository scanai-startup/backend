package com.scanai.api.controllers;

import com.scanai.api.domain.produtoadcpedecuba.dto.DadosAtualizarProdutoAdicionadoPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosDetalhamentoProdutoAdicionadoPeDeCuba;
import com.scanai.api.domain.produtoadcvinho.ProdutoAdicionadovinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosAtualizarProdutoAdicionadoVinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosCadastroProdutoAdicionadoVinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosDetalhamentoProdutoAdicionadoVinho;
import com.scanai.api.repositories.ProdutoAdicionadovinhoRepository;
import com.scanai.api.services.ProdutoAdicionadovinhoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produtoadcvinho")
public class ProdutoAdicionadovinhoController {

    @Autowired
    ProdutoAdicionadovinhoService service;


    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoProdutoAdicionadoVinho> register(@RequestBody @Valid DadosCadastroProdutoAdicionadoVinho data, UriComponentsBuilder uriBuilder){
        ProdutoAdicionadovinho newProdutoadcvinho = service.register(data);
        var uri = uriBuilder.path("produtoadcvinho/register/{id}").buildAndExpand(newProdutoadcvinho.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoProdutoAdicionadoVinho(newProdutoadcvinho));
    }

    @GetMapping("/getAllByVinhoId/{fkVinho}")
    public ResponseEntity<List<DadosDetalhamentoProdutoAdicionadoVinho>> getAllByVinhoId(@PathVariable Long fkVinho) {
        List<DadosDetalhamentoProdutoAdicionadoVinho> lista = service.getAllByVinhoId(fkVinho);
        return ResponseEntity.ok(lista);
    }

    @Transactional
    @PutMapping("/update")
    public ResponseEntity<DadosDetalhamentoProdutoAdicionadoVinho> update(@RequestBody @Valid DadosAtualizarProdutoAdicionadoVinho data){
        return ResponseEntity.ok().body(service.update(data));
    }

    @DeleteMapping("/hardDelete/{id}")
    public ResponseEntity<?> hardDelete(@PathVariable Long id){
        service.hardDelete(id);
        return ResponseEntity.ok().build();
    }
}
