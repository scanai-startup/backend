package com.scanai.api.repositories;

import com.scanai.api.domain.analiseprefermentacao.Analiseprefermentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnaliseprefermentacaoRepository extends JpaRepository<Analiseprefermentacao, Long> {
}
