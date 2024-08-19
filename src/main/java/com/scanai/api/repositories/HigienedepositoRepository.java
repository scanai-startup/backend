package com.scanai.api.repositories;

import com.scanai.api.domain.higienedeposito.Higienedeposito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HigienedepositoRepository extends JpaRepository<Higienedeposito, Long> {
    public List<Higienedeposito> findAllByFkdeposito(Long fkdeposito);
}
