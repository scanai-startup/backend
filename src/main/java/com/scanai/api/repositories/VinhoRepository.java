package com.scanai.api.repositories;

import com.scanai.api.domain.vinho.Vinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface VinhoRepository extends JpaRepository<Vinho, Long> {
    List<Vinho> findAllByValidTrue();

    List<Vinho> findAllByDatafimfermentacaoBetween(LocalDate begin, LocalDate end);

    @Query(value = """
    SELECT r.nome AS rotulo, SUM(v.volume) AS total
    FROM tb_vinho v
    JOIN tb_rotulo r ON v.fkrotulo = r.id
    WHERE v.datafimfermentacao BETWEEN :begin AND :end
      AND v.valid = true
    GROUP BY r.nome
    ORDER BY r.nome
""", nativeQuery = true)
    List<Map<String, Object>> getTotalVolumeByPeriodoGrouped(@Param("begin") LocalDate begin,
                                                             @Param("end") LocalDate end);



}
