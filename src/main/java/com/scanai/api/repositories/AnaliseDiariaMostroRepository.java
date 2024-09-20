package com.scanai.api.repositories;

import com.scanai.api.domain.analisediariamostro.AnaliseDiariaMostro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnaliseDiariaMostroRepository extends JpaRepository<AnaliseDiariaMostro,Long> {
}
