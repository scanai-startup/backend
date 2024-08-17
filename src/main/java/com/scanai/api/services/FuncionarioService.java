package com.scanai.api.services;

import com.scanai.api.domain.funcionario.Funcionario;
import com.scanai.api.domain.funcionario.dto.ResetPasswordDTO;
import com.scanai.api.infra.security.TokenService;
import com.scanai.api.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void resetPassword(ResetPasswordDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.matricula(), data.senhaAtual());
        var auth = authenticationManager.authenticate(usernamePassword);

        var funcionario = (Funcionario)auth.getPrincipal();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senhaNova());

        funcionario.setSenha(encryptedPassword);
    }
}
