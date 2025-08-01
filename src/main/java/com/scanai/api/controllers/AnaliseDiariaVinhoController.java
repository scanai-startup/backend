package com.scanai.api.controllers;

import com.scanai.api.domain.analisediariavinho.dto.DadosAtualizarAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosCadastroAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosDetalhamentoAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosListagemAnaliseDiariaVinho;
import com.scanai.api.services.AnaliseDiariaVinhoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/analisediariavinho")

public class AnaliseDiariaVinhoController {
    @Autowired
    AnaliseDiariaVinhoService analiseDiariaVinhoService;

    @Transactional
    @PostMapping("/register")
    public DadosDetalhamentoAnaliseDiariaVinho register(@RequestBody @Valid DadosCadastroAnaliseDiariaVinho dados){
        var analiseDiariaVinho = analiseDiariaVinhoService.register(dados);
        return new DadosDetalhamentoAnaliseDiariaVinho(analiseDiariaVinho);
    }

    @GetMapping("/getAll")
    public List<DadosListagemAnaliseDiariaVinho> getAll(){
        return analiseDiariaVinhoService.getAll();
    }

    @GetMapping("/getElement/{id}")
    public DadosDetalhamentoAnaliseDiariaVinho getElement(@PathVariable Long id){
        var analiseDiariaVinho = analiseDiariaVinhoService.getElement(id);
        return new DadosDetalhamentoAnaliseDiariaVinho(analiseDiariaVinho);
    }

    @PutMapping("/update")
    @Transactional
    public DadosDetalhamentoAnaliseDiariaVinho update(@RequestBody DadosAtualizarAnaliseDiariaVinho dados){
        return analiseDiariaVinhoService.update(dados);
    }

    @DeleteMapping("hardDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void hardDelete(@PathVariable Long id){
        analiseDiariaVinhoService.hardDelete(id);
    }

}
