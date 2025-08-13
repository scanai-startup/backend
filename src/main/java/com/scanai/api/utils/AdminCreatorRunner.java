package com.scanai.api.utils;

import com.scanai.api.repositories.FuncionarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AdminCreatorRunner implements CommandLineRunner {
    @Value("${MATRICULA}")
    private String matricula;

    @Value("${EMAIL}")
    private String email;

    @Value("${CPF}")
    private String cpf;

    @Value("${NOME}")
    private String nome;

    @Value("${SENHA}")
    private String senha;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    private final EntityManager entityManager;

    public AdminCreatorRunner(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (matricula == null || email == null || cpf == null || nome == null || senha == null) {
            System.err.println("❌ Parâmetros obrigatórios: --matricula= --email= --cpf= --nome= --senha=, ADMIN não criado");
            return;
        }

        this.createAdmin(nome, matricula, email, cpf, senha);
    }

    @Transactional
    private void createAdmin(String nome, String matricula, String email, String cpf, String senha){
        String checkSQL = "SELECT COUNT(1) " +
                "FROM tb_funcionario WHERE matricula = :matricula " +
                "OR email = :email OR cpf = :cpf";
        Query checkQuery = entityManager.createNativeQuery(checkSQL)
            .setParameter("matricula", matricula)
            .setParameter("email", email)
            .setParameter("cpf", cpf);

        if ((Long) checkQuery.getSingleResult() > 0) {
            System.out.println("❌ Usuário com esta matrícula ou email ou cpf já existe ");
            return;
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(senha);

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
