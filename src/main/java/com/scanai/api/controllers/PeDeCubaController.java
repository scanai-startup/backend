package com.scanai.api.controllers;

import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;
import com.scanai.api.domain.pedecuba.dto.DadosDetalhamentoPeDeCuba;
import com.scanai.api.services.PedecubaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peDeCuba")
public class PeDeCubaController {

    @Autowired
    private PedecubaService service;

    @PostMapping("/register")
    public DadosDetalhamentoPeDeCuba register(@RequestBody @Valid DadosCadastroPeDeCuba data){
        Pedecuba newPedecuba = service.register(data);
        return new DadosDetalhamentoPeDeCuba(newPedecuba);
    }

    @GetMapping("/getALl")
    public List<DadosDetalhamentoPeDeCuba> getAll(){
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
        service.activate(id);
    }

}

