package com.scanai.api.controllers;

import com.scanai.api.domain.funcionario.dto.AuthenticationDTO;
import com.scanai.api.domain.funcionario.Funcionario;
import com.scanai.api.domain.funcionario.dto.LoginResponseDTO;
import com.scanai.api.domain.funcionario.dto.RegisterDTO;
import com.scanai.api.infra.security.TokenService;
import com.scanai.api.repositories.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.matricula(), data.senha());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Funcionario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data, UriComponentsBuilder uriBuilder){
        if(this.repository.findByMatricula(data.matricula()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Funcionario newFuncionario = new Funcionario(data.matricula(), encryptedPassword, data.role(), data.nome(), data.email());
        var uri = uriBuilder.path("auth/register/{id}").buildAndExpand(newFuncionario.getId()).toUri();

        this.repository.save(newFuncionario);

        return ResponseEntity.created(uri).build();
    }
}
