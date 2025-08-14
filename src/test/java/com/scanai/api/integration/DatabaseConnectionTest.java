package com.scanai.api.integration;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;

@Tag("manual")
@SpringBootTest
@ActiveProfiles("test")
class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testDatabaseConnection() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("✅ Conexão com banco estabelecida com sucesso!");
            System.out.println("URL: " + connection.getMetaData().getURL());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Usuario: " + connection.getMetaData().getUserName());

            // Inserir usuário após a conexão
            insertUser();
        }
    }

    private void insertUser() {
        String sql = "INSERT INTO tb_funcionario (matricula, email, nome, cpf, senha, role) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            "123",
            "email@email",
            "usuario1",
            "1234567891011",
            "$2a$12$uYHEy.ePOmJCSS/3tKjEmOj5L04SMOBJMqZT1VeZnjrJPEvlqS8bu",
            "ADMIN"
        );

        jdbcTemplate.update(sql,
            "12345",
            "email2@email",
            "usuario2",
            "11111111111",
            "$2a$12$uYHEy.ePOmJCSS/3tKjEmOj5L04SMOBJMqZT1VeZnjrJPEvlqS8bu",
            "FUNCIONARIO"
        );
        System.out.println("✅ Usuário inserido com sucesso!");
    }
}