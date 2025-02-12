package com.scanai.api.infra.services;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DevDBService {

    private final EntityManager entityManager;

    public DevDBService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void loadData() {
        entityManager.createNativeQuery("insert into tb_funcionario(matricula, email, nome, cpf, senha, role) " +
                "values (123, 'email@email', 'usuario1', '1234567891011', " +
                "'$2a$12$uYHEy.ePOmJCSS/3tKjEmOj5L04SMOBJMqZT1VeZnjrJPEvlqS8bu', 'ADMIN');").executeUpdate();

        entityManager.createNativeQuery("insert into tb_funcionario(matricula, email, nome, cpf, senha, role) " +
                "values (12345, 'email2@email', 'usuario2', '11111111111', " +
                "'$2a$12$uYHEy.ePOmJCSS/3tKjEmOj5L04SMOBJMqZT1VeZnjrJPEvlqS8bu', 'FUNCIONARIO');").executeUpdate();

        System.out.println("Usuário ADM (mat:123, sen:senha123) e Usuário FUN (mat:12345, sen:senha123) criados com sucesso");
    }
}
