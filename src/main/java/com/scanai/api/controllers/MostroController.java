package com.scanai.api.controllers;

import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import com.scanai.api.domain.mostro.dto.DadosDetalhamentoMostro;
import com.scanai.api.domain.mostro.dto.DadosListagemMostro;
import com.scanai.api.repositories.MostroRepository;
import com.scanai.api.services.MostroServiceInterface;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/mostro")
public class MostroController {

    @Autowired
    private MostroServiceInterface service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public DadosDetalhamentoMostro register(@RequestBody @Valid DadosCadastroMostro data){
        return service.register(data);
    }

    @GetMapping("/getAll")
    public List<DadosListagemMostro> getAll(){
        return service.getAll();
    }

    @PutMapping("/softDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void softDelete(@PathVariable Long id){
        service.softDelete(id);
    }

    @PutMapping("/activate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void activate(@PathVariable Long id){
        service.softDelete(id);
    }

}

