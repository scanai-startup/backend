package com.scanai.api.controllers;

import com.scanai.api.domain.liberacao.dto.DadosAtualizarLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosCadastroLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosDetalhamentoLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosListagemLiberacao;
import com.scanai.api.services.LiberacaoServiceInterface;
import com.scanai.api.services.implement.LiberacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/liberacao")

public class LiberacaoController {
    @Autowired
    LiberacaoServiceInterface liberacaoService;

    @Transactional
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DadosDetalhamentoLiberacao register(@RequestBody @Valid DadosCadastroLiberacao dados){
        var liberacao = liberacaoService.register(dados);
        return new DadosDetalhamentoLiberacao(liberacao);
    }

    @GetMapping("/getAll")
    public List<DadosListagemLiberacao> getAll(){
        return liberacaoService.getAll();
    }

    @GetMapping("/getElement/{id}")
    public DadosDetalhamentoLiberacao getElement(@PathVariable Long id){
        var liberacao = liberacaoService.getElement(id);
        return new DadosDetalhamentoLiberacao(liberacao);
    }

    @PutMapping("/update")
    @Transactional
    public DadosDetalhamentoLiberacao update(@RequestBody DadosAtualizarLiberacao dados){
        return liberacaoService.update(dados);
    }

    @DeleteMapping("hardDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void hardDelete(@PathVariable Long id){
        liberacaoService.hardDelete(id);
    }

}
