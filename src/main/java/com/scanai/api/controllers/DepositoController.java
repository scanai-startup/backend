package com.scanai.api.controllers;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.*;
import com.scanai.api.repositories.DepositoRepository;
import com.scanai.api.services.implement.DepositoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/deposito")
public class DepositoController {

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    private DepositoService depositoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    @Transactional
    public DadosDetalhamentoDeposito register(@RequestBody @Valid DadosCadastroDeposito data){
        Deposito newDeposito = depositoService.register(data);
        return new DadosDetalhamentoDeposito(newDeposito);
    }

    @PutMapping("/update")
    @Transactional
    public DadosDetalhamentoDeposito update(@RequestBody @Valid DadosAtualizarDeposito data){
        Deposito deposito = depositoService.update(data);
        return new DadosDetalhamentoDeposito(deposito);
    }

    @GetMapping("/getAll")
    public List<DadosListagemDeposito> getAll(){
        return depositoRepository.findAllByValidTrue().stream().map(DadosListagemDeposito::new).toList();
    }

    @PutMapping("/softDelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void softDelete(@PathVariable Long id){
        depositoService.softDelete(depositoRepository.getReferenceById(id));
    }

    @PutMapping("/activate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void activate(@PathVariable Long id){
        depositoService.activate(depositoRepository.getReferenceById(id));
    }

    @GetMapping("/getElement/{id}")
    public DadosDetalhamentoDeposito getElement(@PathVariable Long id){
        Deposito deposito = depositoService.getElement(id);
        return new DadosDetalhamentoDeposito(deposito);
    }

    @GetMapping("/getDepositoWithIdWithInformations/{id}")
    public DadosInformacoesDepositos getDepositoWithIdWithInformations(@PathVariable Long id){
        return depositoService.getDepositoWithIdWithInformations(id);

    }

    @GetMapping("/getAllDepositosWithInformations")
    public List<DadosInformacoesDepositos> getAllDepositosWithInformations(){
        return depositoService.getAllDepositosWithInformations();
    }

    @PostMapping("/realizarTrasfega")
    public DadosDetalhamentoTrasfegaDeposito realizarTrasfega(@RequestBody @Valid DadosTrasfegaDeposito data) {
        return depositoService.realizarTrasfega(data);
    }

}
