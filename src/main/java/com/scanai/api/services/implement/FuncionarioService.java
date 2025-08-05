package com.scanai.api.services.implement;

import com.scanai.api.domain.funcionario.Funcionario;
import com.scanai.api.domain.funcionario.dto.ResetPasswordDTO;
import com.scanai.api.repositories.FuncionarioRepository;
import com.scanai.api.services.FuncionarioServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService implements FuncionarioServiceInterface {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void resetPassword(ResetPasswordDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.matricula(), data.senhaAtual());
        var auth = authenticationManager.authenticate(usernamePassword);

        Funcionario funcionario = (Funcionario)auth.getPrincipal();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senhaNova());

        funcionario.setSenha(encryptedPassword);

        funcionarioRepository.save(funcionario);
    }
}
