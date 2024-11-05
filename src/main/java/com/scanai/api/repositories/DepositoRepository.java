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
            CASE
                       WHEN adm.temperatura IS NOT NULL THEN 'Mostro'
                       WHEN adp.temperatura IS NOT NULL THEN 'Pé de Cuba'
                       WHEN adv.temperatura IS NOT NULL THEN 'Vinho'
                       ELSE 'Desconhecido'
            END AS conteudo,
        ROUND(COALESCE(adm.temperatura, adp.temperatura, adv.temperatura), 2) AS temperatura,
        ROUND(COALESCE(adm.densidade, adp.densidade, adv.densidade), 2) AS densidade,
        ROUND(adv.pressao, 2) AS pressao
    FROM tb_deposito AS d
    LEFT JOIN tb_deposito_mostro AS dm ON d.id = dm.fkdeposito AND dm.datafim IS NULL
    LEFT JOIN tb_mostro AS m ON dm.fkmostro = m.id
    LEFT JOIN tb_analise_diaria_mostro AS adm ON adm.fkmostro = m.id
    LEFT JOIN tb_deposito_pedecuba AS dp ON d.id = dp.fkdeposito AND dp.datafim IS NULL
    LEFT JOIN tb_pe_de_cuba AS p ON dp.fkpedecuba = p.id
    LEFT JOIN tb_analise_diaria_pedecuba AS adp ON adp.fkpedecuba = p.id
    LEFT JOIN tb_deposito_vinho AS dv ON d.id = dv.fkdeposito AND dv.datafim IS NULL
    LEFT JOIN tb_vinho AS v ON dv.fkvinho = v.id
    LEFT JOIN tb_analise_diaria_vinho AS adv ON adv.fkvinho = v.id
    
        WHERE (dm.fkdeposito IS NOT NULL OR dp.fkdeposito IS NOT NULL OR dv.fkdeposito IS NOT NULL)
    
    UNION ALL
    
    SELECT d.numerodeposito AS deposito,
            NULL AS conteudo,
            NULL AS temperatura,
            NULL AS desidade,
            NULL AS pressao
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
