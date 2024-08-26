package com.scanai.api.repositories;

import com.scanai.api.domain.viticultor.Viticultor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViticultorRepository extends JpaRepository<Viticultor, Long> {

}
