package com.scanai.api.repositories;

import com.scanai.api.domain.analisediariavinho.AnaliseDiariaVinho;
import com.scanai.api.services.AnaliseDiariaVinhoService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnaliseDiariaVinhoRepository extends JpaRepository<AnaliseDiariaVinho, Long> {
}
