package com.scanai.api.controllers;

import com.scanai.api.domain.rotulo.DTO.DadosCadastroRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosDetalhamentoRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosAtualizarRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosListagemRotulo;
import com.scanai.api.services.RotuloService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/rotulo")

public class RotuloController {
    @Autowired
    private RotuloService rotuloService;

    @Transactional
    @PostMapping("/register")
    public DadosDetalhamentoRotulo register(@RequestBody @Valid DadosCadastroRotulo dados){
        var rotulo = rotuloService.register(dados);
        return new DadosDetalhamentoRotulo(rotulo);
    }

    @GetMapping("/getAll")
    public List<DadosListagemRotulo> getAll(){
        return rotuloService.listAll();
    }

    @GetMapping("/getElement/{id}")
    public DadosDetalhamentoRotulo getElement(@PathVariable Long id){
        var rotulo = rotuloService.getElement(id);
        return new DadosDetalhamentoRotulo(rotulo);
    }

    @PutMapping("/update")
    @Transactional
    public DadosDetalhamentoRotulo update(@RequestBody DadosAtualizarRotulo dados){
        return rotuloService.update(dados);
    }

    @Transactional
    @DeleteMapping("hardDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hardDelete(@PathVariable Long id){
        rotuloService.hardDelete(id);
    }

}
