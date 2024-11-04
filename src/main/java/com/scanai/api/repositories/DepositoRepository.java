package com.scanai.api.repositories;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.DadosInformacoesDepositos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {

    public Deposito findByNumerodeposito(String numerodeposito);

    public List<Deposito> findAllByValidTrue();

    @Query(value = """
    SELECT d.numerodeposito AS deposito, 
           ROUND(adm.temperatura, 2) AS tempMostro, ROUND(adm.densidade, 2) AS densMostro,
           ROUND(adp.temperatura, 2) AS tempPedecuba, ROUND(adp.densidade, 2) AS densPedecuba,
           ROUND(adv.temperatura, 2) AS tempVinho, ROUND(adv.densidade, 2) AS densVinho,
           ROUND(adv.pressao, 2) AS pressVinho
    FROM tb_deposito AS d
    INNER JOIN tb_deposito_mostro AS dm ON d.id = dm.fkdeposito
    INNER JOIN tb_mostro AS m ON dm.fkmostro = m.id
    INNER JOIN tb_analise_diaria_mostro AS adm ON adm.fkmostro = m.id
    INNER JOIN tb_deposito_pedecuba AS dp ON d.id = dp.fkdeposito
    INNER JOIN tb_pe_de_cuba AS p ON dp.fkpedecuba = p.id
    INNER JOIN tb_analise_diaria_pedecuba AS adp ON adp.fkpedecuba = p.id
    INNER JOIN tb_deposito_vinho AS dv ON d.id = dv.fkdeposito
    INNER JOIN tb_vinho AS v ON dv.fkvinho = v.id
    INNER JOIN tb_analise_diaria_vinho AS adv ON adv.fkvinho = v.id
    WHERE dm.datafim IS NULL 
      AND dp.datafim IS NULL 
      AND dv.datafim IS NULL 

    UNION ALL

    SELECT d.numerodeposito AS deposito,
           NULL AS tempMostro, NULL AS densMostro,
           NULL AS tempPedecuba, NULL AS densPedecuba,
           NULL AS tempVinho, NULL AS densVinho,
           NULL AS pressVinho
    FROM tb_deposito AS d
    LEFT JOIN tb_deposito_mostro AS dm ON d.id = dm.fkdeposito AND dm.datafim IS NULL
    LEFT JOIN tb_deposito_pedecuba AS dp ON d.id = dp.fkdeposito AND dp.datafim IS NULL
    LEFT JOIN tb_deposito_vinho AS dv ON d.id = dv.fkdeposito AND dv.datafim IS NULL
    WHERE dm.fkdeposito IS NULL
      AND dp.fkdeposito IS NULL
      AND dv.fkdeposito IS NULL
    ORDER BY deposito
    """, nativeQuery = true)
    public List<DadosInformacoesDepositos> findDepositosComAnalises();
}
