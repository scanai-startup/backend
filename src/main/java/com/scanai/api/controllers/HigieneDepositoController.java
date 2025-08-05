package com.scanai.api.controllers;

import com.scanai.api.domain.higienedeposito.Higienedeposito;
import com.scanai.api.domain.higienedeposito.dto.DadosCadastroHigieneDeposito;
import com.scanai.api.domain.higienedeposito.dto.DadosDetalhamentoHigieneDeposito;
import com.scanai.api.domain.higienedeposito.dto.DadosListagemHigieneDeposito;
import com.scanai.api.repositories.HigienedepositoRepository;
import com.scanai.api.services.HigieneDepositoServiceInterface;
import com.scanai.api.services.implement.HigieneDepositoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/higieneDeposito")
public class HigieneDepositoController {

    @Autowired
    private HigieneDepositoServiceInterface service;

    @Autowired
    private HigienedepositoRepository repository;

    @PostMapping("/register")
    @Transactional
    public DadosDetalhamentoHigieneDeposito register(@RequestBody @Valid DadosCadastroHigieneDeposito data){
        Higienedeposito newHigienedeposito = service.register(data);
        return new DadosDetalhamentoHigieneDeposito(newHigienedeposito);
    }

    @GetMapping("/listByFk/{fk}")
    public List<DadosListagemHigieneDeposito> list(@PathVariable Long fk){
        return repository.findAllByFkdeposito(fk).stream().map(DadosListagemHigieneDeposito::new).toList();
    }

    @DeleteMapping("/hardDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void hardDelete(@PathVariable Long id){
        repository.deleteById(id);
    }
}
