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

    @PostMapping("/register")
    public DadosDetalhamentoDeposito register(@RequestBody @Valid DadosCadastroDeposito data){
        return depositoService.register(data);
    }

    @PutMapping("/update")
    @Transactional
    public DadosDetalhamentoDeposito update(@RequestBody @Valid DadosAtualizarDeposito data){
        return depositoService.update(data);
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
        return depositoService.getElement(id);
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
