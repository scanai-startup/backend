package com.scanai.api.controllers;

import com.scanai.api.domain.vinculodepositoremessas.dto.DadosCadastroVinculoDepositoRemessas;
import com.scanai.api.domain.vinculodepositoremessas.dto.DadosDetalhamentoVinculoDepositoRemessas;
import com.scanai.api.domain.vinculodepositovinho.dto.DadosCadastroVinculoDepositoVinho;
import com.scanai.api.domain.vinculodepositovinho.dto.DadosDetalhamentoVinculoDepositoVinho;
import com.scanai.api.services.VinculoDepositoRemessasService;
import com.scanai.api.services.VinculoDepositoVinhoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vinculodepositovinho")
public class VinculoDepositoVinhoController {
    @Autowired
    private VinculoDepositoVinhoService service;

    @Transactional
    @PostMapping
    public ResponseEntity<DadosDetalhamentoVinculoDepositoVinho> register(@RequestBody DadosCadastroVinculoDepositoVinho data, UriComponentsBuilder uriBuilder){
        DadosDetalhamentoVinculoDepositoVinho detalhamento = service.vincularDepositoVinho(data);
        return ResponseEntity.ok().body(detalhamento);
    }
}
