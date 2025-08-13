package com.scanai.api.controllers;

import com.scanai.api.domain.produtoadcvinho.ProdutoAdicionadovinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosAtualizarProdutoAdicionadoVinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosCadastroProdutoAdicionadoVinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosDetalhamentoProdutoAdicionadoVinho;
import com.scanai.api.services.ProdutoAdicionadoVinhoServiceInterface;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtoAdcVinho")
public class ProdutoAdicionadoVinhoController {

    @Autowired
    ProdutoAdicionadoVinhoServiceInterface service;

    @PostMapping("/register")
    public DadosDetalhamentoProdutoAdicionadoVinho register(@RequestBody @Valid DadosCadastroProdutoAdicionadoVinho data){
        return service.register(data);
    }

    @GetMapping("/getAllByVinhoId/{fkVinho}")
    public List<DadosDetalhamentoProdutoAdicionadoVinho> getAllByVinhoId(@PathVariable Long fkVinho) {
        return service.getAllByVinhoId(fkVinho);
    }

    @Transactional
    @PutMapping("/update")
    public DadosDetalhamentoProdutoAdicionadoVinho update(@RequestBody @Valid DadosAtualizarProdutoAdicionadoVinho data){
        return service.update(data);
    }

    @DeleteMapping("/hardDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hardDelete(@PathVariable Long id){
        service.hardDelete(id);
    }
}
