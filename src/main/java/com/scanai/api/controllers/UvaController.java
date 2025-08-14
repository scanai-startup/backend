package com.scanai.api.controllers;

import com.scanai.api.domain.uva.Uva;
import com.scanai.api.domain.uva.dto.DadosAtualizarUva;
import com.scanai.api.domain.uva.dto.DadosCadastroUva;
import com.scanai.api.domain.uva.dto.DadosDetalhamentoUva;
import com.scanai.api.domain.uva.dto.DadosListagemUva;
import com.scanai.api.services.UvaServiceInterface;
import com.scanai.api.services.implement.UvaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/uva")
public class UvaController {

    @Autowired
    private UvaServiceInterface uvaService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public DadosDetalhamentoUva register(@RequestBody @Valid DadosCadastroUva dados){
        return uvaService.register(dados);
    }

    @GetMapping("/getAllByValidTrue")
    public List<DadosListagemUva> getAllByValidTrue(){
        return uvaService.listAllByValidTrue();
    }

    @GetMapping("/getAll")
    public List<DadosListagemUva> getAll(){
        return uvaService.listAll();
    }

    @GetMapping("getElement/{id}")
    public DadosDetalhamentoUva getElement(@PathVariable Long id){
        return uvaService.getElement(id);
    }

    @PutMapping("/update")
    @Transactional
    public DadosDetalhamentoUva update(@RequestBody DadosAtualizarUva dados){
        return uvaService.update(dados);
    }

    @DeleteMapping("hardDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hardDelete(@PathVariable Long id){
        uvaService.hardDelete(id);
    }

    @DeleteMapping("/softDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void softDelete(@PathVariable Long id){
        uvaService.softDelete(id);
    }

    @DeleteMapping("/activate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void activate(@PathVariable Long id){
        uvaService.activate(id);
    }
}