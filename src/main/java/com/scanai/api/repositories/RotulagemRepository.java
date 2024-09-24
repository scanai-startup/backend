package com.scanai.api.repositories;

import com.scanai.api.domain.rotulagem.Rotulagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RotulagemRepository extends JpaRepository<Rotulagem, Long> {
}
