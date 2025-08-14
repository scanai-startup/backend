package com.scanai.api.controllers;

import com.scanai.api.domain.vinculodepositovinho.dto.DadosCadastroVinculoDepositoVinho;
import com.scanai.api.domain.vinculodepositovinho.dto.DadosDetalhamentoVinculoDepositoVinho;
import com.scanai.api.services.implement.VinculoDepositoVinhoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vinculoDepositoVinho")
public class VinculoDepositoVinhoController {

    @Autowired
    private VinculoDepositoVinhoService service;

    @Transactional
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DadosDetalhamentoVinculoDepositoVinho register(@RequestBody DadosCadastroVinculoDepositoVinho data){
        return service.vincularDepositoVinho(data);
    }
}
