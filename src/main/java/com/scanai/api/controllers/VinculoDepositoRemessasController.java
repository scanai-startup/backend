package com.scanai.api.controllers;

import com.scanai.api.domain.vinculodepositoremessas.dto.DadosDetalhamentoVinculoDepositoRemessas;
import com.scanai.api.domain.vinculodepositoremessas.dto.DadosCadastroVinculoDepositoRemessas;
import com.scanai.api.services.VinculoDepositoRemessasServiceInterface;
import com.scanai.api.services.implement.VinculoDepositoRemessasService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vinculoDepositoRemessas")
public class VinculoDepositoRemessasController {

    @Autowired
    private VinculoDepositoRemessasServiceInterface service;

    @Transactional
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DadosDetalhamentoVinculoDepositoRemessas register(@RequestBody DadosCadastroVinculoDepositoRemessas data){
        return service.vincularDepositoRemessa(data);
    }
}
