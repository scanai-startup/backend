package com.scanai.api.controllers;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.RegisterDepositoDTO;
import com.scanai.api.repositories.DepositoRepository;
import com.scanai.api.services.DepositoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/deposito")
public class DepositoController {

    @Autowired
    DepositoRepository repository;

    @Autowired
    DepositoService service;

    @PostMapping("/register")
    public ResponseEntity regiterDeposito(@RequestBody @Valid RegisterDepositoDTO data, UriComponentsBuilder uriBuilder){
        Deposito newDeposito = service.createDeposito(data);
        if(newDeposito == null){
            return ResponseEntity.badRequest().build();//tratar exception posteriormente;
        }
        repository.save(newDeposito);
        var uri = uriBuilder.path("deposito/register/{id}").buildAndExpand(newDeposito.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/invalidate/{id}")
    @Transactional
    public ResponseEntity invalidateDeposito(@PathVariable Long id){
        service.invalidadeDeposito(repository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/validate/{id}")
    @Transactional
    public ResponseEntity validateDeposito(@PathVariable Long id){
        service.validadeDeposito(repository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

}
