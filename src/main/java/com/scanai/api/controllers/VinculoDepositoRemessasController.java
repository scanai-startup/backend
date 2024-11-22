package com.scanai.api.controllers;

import com.scanai.api.domain.vinculodepositoremessas.dto.DadosDetalhamentoVinculoDepositoRemessas;
import com.scanai.api.domain.vinculodepositoremessas.dto.DadosCadastroVinculoDepositoRemessas;
import com.scanai.api.services.VinculoDepositoRemessasService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vinculodepositoremessas")
public class VinculoDepositoRemessasController {

    @Autowired
    private VinculoDepositoRemessasService service;

    @Transactional
    @PostMapping
    public ResponseEntity<DadosDetalhamentoVinculoDepositoRemessas> register(@RequestBody DadosCadastroVinculoDepositoRemessas data, UriComponentsBuilder uriBuilder){
        DadosDetalhamentoVinculoDepositoRemessas detalhamento = service.vincularDepositoRemessa(data);
        return ResponseEntity.ok().body(detalhamento);
    }
}
