package com.scanai.api.repositories;

import com.scanai.api.domain.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    //teste unitario criado para ficar de exemplo para quando for de fato necessária a criação de um teste para repository!!!
    UserDetails findByMatricula(String matricula);

}
