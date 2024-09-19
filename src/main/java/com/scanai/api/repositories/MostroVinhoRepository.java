package com.scanai.api.repositories;

import com.scanai.api.domain.mostrovinho.MostroVinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MostroVinhoRepository extends JpaRepository<MostroVinho, Long> {
}
