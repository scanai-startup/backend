package com.scanai.api.repositories;

import com.scanai.api.domain.rotulo.Rotulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RotuloRepository extends JpaRepository<Rotulo, Long> {
    List<Rotulo> findAllByValidTrue();
}
