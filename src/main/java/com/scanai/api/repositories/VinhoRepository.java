package com.scanai.api.repositories;

import com.scanai.api.domain.vinho.DTO.DadosListagemVinho;
import com.scanai.api.domain.vinho.Vinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VinhoRepository extends JpaRepository<Vinho, Long> {
    List<Vinho> findAllByValidTrue();
}
