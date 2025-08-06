package com.scanai.api.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
@ActiveProfiles("test")
class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void testDatabaseConnection() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("✅ Conexão com banco estabelecida com sucesso!");
            System.out.println("URL: " + connection.getMetaData().getURL());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Usuario: " + connection.getMetaData().getUserName());
        }
    }
}
