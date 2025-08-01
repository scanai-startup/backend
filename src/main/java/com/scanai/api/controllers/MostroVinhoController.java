package com.scanai.api.controllers;

import com.scanai.api.domain.mostrovinho.dto.DadosAtualizarMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosCadastroMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosDetalhamentoMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosListagemMostroVinho;
import com.scanai.api.services.MostroVinhoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/mostroVinho")
public class MostroVinhoController {

    @Autowired
    MostroVinhoService mostroVinhoService;
    public DadosDetalhamentoMostroVinho register(@RequestBody @Valid DadosCadastroMostroVinho dados){
        var mostroVinho = mostroVinhoService.register(dados);
        return new DadosDetalhamentoMostroVinho(mostroVinho);
    }

    @GetMapping("/getAll")
    public List<DadosListagemMostroVinho> getAll(){
        return mostroVinhoService.getAll();
    }

    @GetMapping("/getElement/{id}")
    public DadosDetalhamentoMostroVinho getElement(@PathVariable Long id){
        var mostroVinho = mostroVinhoService.getElement(id);
        return new DadosDetalhamentoMostroVinho(mostroVinho);
    }

    @PutMapping("/update")
    @Transactional
    public DadosDetalhamentoMostroVinho update(@RequestBody DadosAtualizarMostroVinho dados){
        return mostroVinhoService.update(dados);
    }

    @DeleteMapping("hardDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void hardDelete(@PathVariable Long id){
        mostroVinhoService.hardDelete(id);
    }

    @DeleteMapping("/softDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void softDelete(@PathVariable Long id){
        mostroVinhoService.softDelete(id);
    }
}
