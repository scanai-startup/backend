package com.scanai.api.infra.config;

import com.scanai.api.infra.services.DevDBService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevDBConfig {

    @Bean
    public CommandLineRunner loadData(DevDBService devDBService) {
        return args -> devDBService.loadData(); // Chama o serviço, que já está transacional
    }
}
