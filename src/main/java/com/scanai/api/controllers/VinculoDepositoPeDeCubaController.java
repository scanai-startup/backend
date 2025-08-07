package com.scanai.api.controllers;

import com.scanai.api.domain.vinculodepositopedecuba.dto.DadosCadastroVinculoDepositoPedecuba;
import com.scanai.api.domain.vinculodepositopedecuba.dto.DadosDetalhamentoVinculoDepositoPedecuba;
import com.scanai.api.services.VinculoDepositoPeDeCubaServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vinculoDepositoPeDeCuba")
public class VinculoDepositoPeDeCubaController {
    @Autowired
    private VinculoDepositoPeDeCubaServiceInterface service;

    @Transactional
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DadosDetalhamentoVinculoDepositoPedecuba register(@RequestBody DadosCadastroVinculoDepositoPedecuba data){
        return service.vincularDepositoPedecuba(data);
    }
}
