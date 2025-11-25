package com.scanai.api.controllers;

import com.scanai.api.domain.vinho.DTO.DadosAtualizarVinho;
import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import com.scanai.api.domain.vinho.DTO.DadosDetalhamentoVinho;
import com.scanai.api.domain.vinho.DTO.DadosListagemVinho;
import com.scanai.api.services.implement.VinhoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vinho")

public class VinhoController {

    @Autowired
    VinhoService vinhoService;

    @Transactional
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DadosDetalhamentoVinho register(@RequestBody @Valid DadosCadastroVinho dados){
        var vinho = vinhoService.register(dados);
        return new DadosDetalhamentoVinho(vinho);
    }

    @GetMapping("/getAll")
    public List<DadosListagemVinho> getAll(){
        return vinhoService.getAll();
    }

    @GetMapping("/getElement/{id}")
    public DadosDetalhamentoVinho getElement(@PathVariable Long id){
        var vinho = vinhoService.getElement(id);
        return new DadosDetalhamentoVinho(vinho);
    }

    @PutMapping("/update")
    @Transactional
    public DadosDetalhamentoVinho update(@RequestBody DadosAtualizarVinho dados){
        return vinhoService.update(dados);
    }

    @DeleteMapping("hardDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void hardDelete(@PathVariable Long id){
        vinhoService.hardDelete(id);
    }

    @DeleteMapping("/softDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void softDelete(@PathVariable Long id){
        vinhoService.softDelete(id);
    }

    @PostMapping("/activate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void activate(@PathVariable Long id){
        vinhoService.activate(id);
    }

    @GetMapping("/getVinhoBetween/{begin}/{end}")
    public List<Map<String, Object>> getVinhoBetween(@PathVariable LocalDate begin,
                                                     @PathVariable LocalDate end) {
        return vinhoService.getVinhoProductionPeriodGrouped(begin, end);
    }

}
