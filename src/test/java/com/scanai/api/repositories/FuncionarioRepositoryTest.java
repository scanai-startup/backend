package com.scanai.api.repositories;

import com.scanai.api.domain.funcionario.Funcionario;
import com.scanai.api.domain.funcionario.dto.RegisterDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.scanai.api.domain.funcionario.FuncionarioRole.FUNCIONARIO;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest //anotação para indicar que o teste é de um repository JPA
@ActiveProfiles("test") //para mudar o perfil para testes
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //para funcionar com o banco mysql
class FuncionarioRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Test
    @DisplayName("Should get Funcionario successfully from DB")
    void findByMatriculaCase1() {
        String matricula = "123";
        RegisterDTO data = new RegisterDTO(matricula, "$2a$12$klq0qolilUca17N6ppQdouT9vqI5040kzPKYLHzAzrWSekuHofonu", FUNCIONARIO, "Joao Silva", "joaosilva@email.com");
        this.createFuncionario(data);

        Optional<UserDetails> foundedFuncionario = Optional.ofNullable(this.funcionarioRepository.findByMatricula(matricula));

        assertThat(foundedFuncionario.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Should not get Funcionario from DB when Funcionario not exists")
    void findByMatriculaCase2() {
        String matricula = "000";
        Optional<UserDetails> foundedFuncionario = Optional.ofNullable(this.funcionarioRepository.findByMatricula(matricula));

        assertThat(foundedFuncionario.isEmpty()).isTrue();

    }

    //é necessário garantir que exista um usuário no banco de dados de teste
    private void createFuncionario(RegisterDTO data){
        Funcionario newFuncionario = new Funcionario(data.matricula(), data.senha(), data.role(), data.nome(), data.email());
        this.entityManager.persist(newFuncionario);
    }
}