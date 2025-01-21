package com.scanai.api.controllers;

import com.scanai.api.domain.vinculodepositopedecuba.dto.DadosCadastroVinculoDepositoPedecuba;
import com.scanai.api.domain.vinculodepositopedecuba.dto.DadosDetalhamentoVinculoDepositoPedecuba;
import com.scanai.api.services.VinculoDepositoPedecubaService;
import com.scanai.api.services.VinculoDepositoRemessasService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vinculodepositopedecuba")
public class VinculoDepositoPedecubaController {
    @Autowired
    private VinculoDepositoPedecubaService service;

    @Transactional
    @PostMapping
    public ResponseEntity<DadosDetalhamentoVinculoDepositoPedecuba> register(@RequestBody DadosCadastroVinculoDepositoPedecuba data, UriComponentsBuilder uriBuilder){
        DadosDetalhamentoVinculoDepositoPedecuba detalhamento = service.vincularDepositoPedecuba(data);
        return ResponseEntity.ok().body(detalhamento);
    }
}
