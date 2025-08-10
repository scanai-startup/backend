package com.scanai.api.repositories;

import com.scanai.api.domain.mostro.Mostro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MostroRepository extends JpaRepository<Mostro, Long> {
    List<Mostro> findAllByValidTrue();
}
