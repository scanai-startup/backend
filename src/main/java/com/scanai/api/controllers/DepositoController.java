package com.scanai.api.controllers;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.DadosCadastroDeposito;
import com.scanai.api.domain.deposito.dto.DadosAtualizarDeposito;
import com.scanai.api.domain.deposito.dto.DadosDetalhamentoDeposito;
import com.scanai.api.domain.deposito.dto.DadosListagemDeposito;
import com.scanai.api.domain.higienedeposito.dto.DadosListagemHigieneDeposito;
import com.scanai.api.repositories.DepositoRepository;
import com.scanai.api.services.DepositoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/deposito")
public class DepositoController {

    @Autowired
    private DepositoRepository repository;

    @Autowired
    private DepositoService service;

    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoDeposito> regiterDeposito(@RequestBody @Valid DadosCadastroDeposito data, UriComponentsBuilder uriBuilder){
        Deposito newDeposito = service.register(data);
        var uri = uriBuilder.path("deposito/register/{id}").buildAndExpand(newDeposito.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoDeposito(newDeposito));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DadosDetalhamentoDeposito> updateDeposito(@RequestBody @Valid DadosAtualizarDeposito data){
        Deposito deposito = service.update(data);
        return ResponseEntity.ok().body(new DadosDetalhamentoDeposito(deposito));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DadosListagemDeposito>> listDepositos(){
        return ResponseEntity.ok().body(repository.findAllByValidTrue().stream().map(DadosListagemDeposito::new).toList());
    }

    @PutMapping("/softDelete/{id}")
    @Transactional
    public ResponseEntity<?> invalidateDeposito(@PathVariable Long id){
        service.softDelete(repository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/activate/{id}")
    @Transactional
    public ResponseEntity<?> validateDeposito(@PathVariable Long id){
        service.activate(repository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

}
