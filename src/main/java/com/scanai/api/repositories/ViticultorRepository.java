package com.scanai.api.repositories;

import com.scanai.api.domain.viticultor.Viticultor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ViticultorRepository extends JpaRepository<Viticultor, Long> {

}
