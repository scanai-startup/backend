package com.scanai.api.controllers;

import com.scanai.api.domain.analisediariamostro.dto.DadosAtualizarAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosCadastroAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosDetalhamentoAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosListagemAnaliseDiariaMostro;
import com.scanai.api.services.AnaliseDiariaMostroServiceInterface;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/analiseDiariaMostro")

public class AnaliseDiariaMostroController {
    @Autowired
    AnaliseDiariaMostroServiceInterface analiseDiariaMostroService;

    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public DadosDetalhamentoAnaliseDiariaMostro register(@RequestBody @Valid DadosCadastroAnaliseDiariaMostro dados){
        return analiseDiariaMostroService.register(dados);
    }

    @GetMapping("/getAll")
    public List<DadosListagemAnaliseDiariaMostro> getAll(){
        return analiseDiariaMostroService.getAll();
    }

    @GetMapping("/getElement/{id}")
    public DadosDetalhamentoAnaliseDiariaMostro getElement(@PathVariable Long id){
        return analiseDiariaMostroService.getElement(id);
    }

    @PutMapping("/update")
    @Transactional
    public DadosDetalhamentoAnaliseDiariaMostro update(@RequestBody DadosAtualizarAnaliseDiariaMostro dados){
        return analiseDiariaMostroService.update(dados);
    }

    @DeleteMapping("hardDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void hardDelete(@PathVariable Long id){
        analiseDiariaMostroService.hardDelete(id);
    }

}
