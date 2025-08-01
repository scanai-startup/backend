package com.scanai.api.controllers;

import com.scanai.api.domain.vinculodepositopedecuba.dto.DadosCadastroVinculoDepositoPedecuba;
import com.scanai.api.domain.vinculodepositopedecuba.dto.DadosDetalhamentoVinculoDepositoPedecuba;
import com.scanai.api.services.VinculoDepositoPedecubaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vinculoDepositoPeDeCuba")
public class VinculoDepositoPeDeCubaController {
    @Autowired
    private VinculoDepositoPedecubaService service;

    @Transactional
    @PostMapping("/register")
    public DadosDetalhamentoVinculoDepositoPedecuba register(@RequestBody DadosCadastroVinculoDepositoPedecuba data){
        return service.vincularDepositoPedecuba(data);
    }
}
