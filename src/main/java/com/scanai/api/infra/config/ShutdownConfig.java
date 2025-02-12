package com.scanai.api.infra.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class ShutdownConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @EventListener(ContextClosedEvent.class)
    public void onShutdown() {
        entityManager.createNativeQuery(
                "DROP SCHEMA scanAiDev;"
        ).executeUpdate();

        System.out.println("🛑 Banco de dados resetado ao desligar a aplicação!");
    }
}
