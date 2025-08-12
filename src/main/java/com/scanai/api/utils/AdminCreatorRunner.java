package com.scanai.api.utils;

import com.scanai.api.repositories.FuncionarioRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AdminCreatorRunner implements CommandLineRunner {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    private final EntityManager entityManager;

    public AdminCreatorRunner(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void run(String... args) {
        String matricula = null;
        String email = null;
        String cpf = null;
        String nome = null;
        String senha = null;

        for (String arg : args) {
            if (arg.startsWith("--matricula=")) {
                matricula = arg.split("=", 2)[1];
            } else if (arg.startsWith("--email=")) {
                email = arg.split("=", 2)[1];
            } else if (arg.startsWith("--cpf=")) {
                cpf = arg.split("=", 2)[1];
            } else if (arg.startsWith("--nome=")) {
                nome = arg.split("=", 2)[1];
            } else if (arg.startsWith("--senha=")) {
                senha = arg.split("=", 2)[1];
            }
        }

        if (matricula == null || email == null || cpf == null || nome == null || senha == null) {
            System.err.println("❌ Parâmetros obrigatórios: --matricula= --email= --cpf= --nome= --senha=, ADMIN não criado");
            return;
        }

        this.createAdmin(nome, matricula, email, cpf, senha);
    }

    @Transactional
    private void createAdmin(String nome, String matricula, String email, String cpf, String senha){
        String encryptedPassword = new BCryptPasswordEncoder().encode(senha);

        if (funcionarioRepository.findByMatricula(matricula).isPresent()) {
            System.out.println("❌ Usuário com esta matrícula ou email já existe ");
            return;
        }

        entityManager.createNativeQuery("insert into tb_funcionario(matricula, email, nome, cpf, senha, role) " +
                    "values (:matricula, :email, :nome, :cpf, " +
                    ":senha, 'ADMIN');")
                .setParameter("matricula", matricula)
                .setParameter("nome", nome)
                .setParameter("email", email)
                .setParameter("cpf", cpf)
                .setParameter("senha", encryptedPassword)
                .executeUpdate();

        System.out.println("✅ Usuário ADM criado com sucesso: " + matricula);
    }
}
