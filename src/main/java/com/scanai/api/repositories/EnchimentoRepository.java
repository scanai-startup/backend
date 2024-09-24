package com.scanai.api.repositories;

import com.scanai.api.domain.enchimento.Enchimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnchimentoRepository extends JpaRepository<Enchimento,Long> {
}
