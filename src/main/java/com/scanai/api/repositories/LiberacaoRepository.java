package com.scanai.api.repositories;

import com.scanai.api.domain.liberacao.Liberacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiberacaoRepository extends JpaRepository<Liberacao, Long> {

}
