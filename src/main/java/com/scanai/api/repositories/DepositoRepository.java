package com.scanai.api.repositories;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.DadosInformacoesDepositos;
import com.scanai.api.domain.depositomostro.DepositoMostro;
import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositovinho.Depositovinho;
import com.scanai.api.domain.mostro.Mostro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {
    public Deposito findDepositoById(Long id);

    public Deposito findByNumerodeposito(String numerodeposito);

    public List<Deposito> findAllByValidTrue();

    @Query(value = """

        WITH UltimaAnaliseMostro AS (
            SELECT adm.fkmostro, MAX(adm.data) AS ultima_data
            FROM tb_analise_diaria_mostro adm
            GROUP BY adm.fkmostro
        ),
        UltimaAnalisePedeCuba AS (
            SELECT adp.fkpedecuba, MAX(adp.data) AS ultima_data
            FROM tb_analise_diaria_pedecuba adp
            GROUP BY adp.fkpedecuba
        ),
        UltimaAnaliseVinho AS (
            SELECT adv.fkvinho, MAX(adv.data) AS ultima_data
            FROM tb_analise_diaria_vinho adv
            GROUP BY adv.fkvinho
        )
        SELECT
            d.numerodeposito AS deposito,
            CASE
                WHEN (dm.fkdeposito IS NOT NULL) THEN 'Mostro'
                WHEN (dp.fkdeposito IS NOT NULL) THEN 'Pé de Cuba'
                WHEN (dv.fkdeposito IS NOT NULL) THEN 'Vinho'
                ELSE NULL
            END AS conteudo,
            CASE
                WHEN (dm.fkdeposito IS NOT NULL) THEN ROUND(ua_mostro.temperatura, 2)
                WHEN (dp.fkdeposito IS NOT NULL) THEN ROUND(ua_pedecuba.temperatura, 2)
                WHEN (dv.fkdeposito IS NOT NULL) THEN ROUND(ua_vinho.temperatura, 2)
                ELSE NULL
            END AS temperatura,
            CASE
                WHEN (dm.fkdeposito IS NOT NULL) THEN ROUND(ua_mostro.densidade, 2)
                WHEN (dp.fkdeposito IS NOT NULL) THEN ROUND(ua_pedecuba.densidade, 2)
                WHEN (dv.fkdeposito IS NOT NULL) THEN ROUND(ua_vinho.densidade, 2)
                ELSE NULL
            END AS densidade,
            CASE
                WHEN (dv.fkdeposito IS NOT NULL AND v.valid = 1) THEN ROUND(ua_vinho.pressao, 2)
                ELSE NULL
            END AS pressao,
            d.id AS idDeposito,
            d.capacidade AS capacidadeDeposito,
            d.numerodeposito AS numeroDeposito,
            d.tipodeposito AS tipoDeposito,
            CASE
                WHEN (dm.fkdeposito IS NOT NULL) THEN m.volume
                WHEN (dp.fkdeposito IS NOT NULL) THEN p.volume
                WHEN (dv.fkdeposito IS NOT NULL) THEN v.volume
                ELSE NULL
            END AS volumeConteudo,
            CASE
                WHEN (dm.fkdeposito IS NOT NULL) THEN m.id
                WHEN (dp.fkdeposito IS NOT NULL) THEN p.id
                WHEN (dv.fkdeposito IS NOT NULL) THEN v.id
                ELSE NULL
            END AS idConteudo
        FROM tb_deposito AS d
        LEFT JOIN tb_deposito_mostro AS dm ON d.id = dm.fkdeposito AND dm.datafim IS NULL
        LEFT JOIN tb_mostro AS m ON dm.fkmostro = m.id
        LEFT JOIN UltimaAnaliseMostro uam ON uam.fkmostro = m.id
        LEFT JOIN tb_analise_diaria_mostro AS ua_mostro ON ua_mostro.fkmostro = uam.fkmostro AND ua_mostro.data = uam.ultima_data
        LEFT JOIN tb_deposito_pedecuba AS dp ON d.id = dp.fkdeposito AND dp.datafim IS NULL
        LEFT JOIN tb_pe_de_cuba AS p ON dp.fkpedecuba = p.id
        LEFT JOIN UltimaAnalisePedeCuba uap ON uap.fkpedecuba = p.id
        LEFT JOIN tb_analise_diaria_pedecuba AS ua_pedecuba ON ua_pedecuba.fkpedecuba = uap.fkpedecuba AND ua_pedecuba.data = uap.ultima_data
        LEFT JOIN tb_deposito_vinho AS dv ON d.id = dv.fkdeposito AND dv.datafim IS NULL
        LEFT JOIN tb_vinho AS v ON dv.fkvinho = v.id
        LEFT JOIN UltimaAnaliseVinho uav ON uav.fkvinho = v.id
        LEFT JOIN tb_analise_diaria_vinho AS ua_vinho ON ua_vinho.fkvinho = uav.fkvinho AND ua_vinho.data = uav.ultima_data
        WHERE (dm.fkdeposito IS NOT NULL OR dp.fkdeposito IS NOT NULL OR dv.fkdeposito IS NOT NULL)
        
        UNION ALL
        
        SELECT
            d.numerodeposito AS deposito,
            NULL AS conteudo,
            NULL AS temperatura,
            NULL AS densidade,
            NULL AS pressao,
            d.id AS idDeposito,
            d.capacidade AS capacidadeDeposito,
            d.numerodeposito AS numeroDeposito,
            d.tipodeposito AS tipoDeposito,
            NULL AS volumeConteudo,
            NULL AS idConteudo
        FROM tb_deposito AS d
        LEFT JOIN tb_deposito_mostro AS dm ON d.id = dm.fkdeposito AND dm.datafim IS NULL
        LEFT JOIN tb_deposito_pedecuba AS dp ON d.id = dp.fkdeposito AND dp.datafim IS NULL
        LEFT JOIN tb_deposito_vinho AS dv ON d.id = dv.fkdeposito AND dv.datafim IS NULL
        WHERE dm.fkdeposito IS NULL
          AND dp.fkdeposito IS NULL
          AND dv.fkdeposito IS NULL
        ORDER BY deposito;
    """, nativeQuery = true)
    public List<DadosInformacoesDepositos> getAllDepositosWithInformations();

    @Query("""
            SELECT dm
            FROM tb_deposito_mostro as dm WHERE dm.fkdeposito = :depositoId AND dm.datafim IS NULL
            """)
    DepositoMostro existsMostroAtivo(@Param("depositoId") Long depositoId);

    @Query("""
            SELECT dp
            FROM tb_deposito_pedecuba as dp WHERE dp.fkdeposito = :depositoId AND dp.datafim IS NULL
            """)
    Depositopedecuba existsPeDeCubaAtivo(@Param("depositoId") Long depositoId);

    @Query("""
            SELECT dv
            FROM tb_deposito_vinho as dv WHERE dv.fkdeposito = :depositoId AND dv.datafim IS NULL
            """)
    Depositovinho existsVinhoAtivo(@Param("depositoId") Long depositoId);

    @Query(value = """
        SELECT d.numerodeposito AS deposito,
            CASE
                WHEN (dm.fkdeposito IS NOT NULL) THEN 'Mostro'
                WHEN (dp.fkdeposito IS NOT NULL) THEN 'Pé de Cuba'
                WHEN (dv.fkdeposito IS NOT NULL) THEN 'Vinho'
                ELSE NULL
            END AS conteudo,
            CASE
                WHEN (dm.fkdeposito IS NOT NULL) THEN ROUND(adm.temperatura, 2)
                WHEN (dp.fkdeposito IS NOT NULL) THEN ROUND(adp.temperatura, 2)
                WHEN (dv.fkdeposito IS NOT NULL) THEN ROUND(adv.temperatura, 2)
                ELSE NULL
            END AS temperatura,
            CASE
                WHEN (dm.fkdeposito IS NOT NULL) THEN ROUND(adm.densidade, 2)
                WHEN (dp.fkdeposito IS NOT NULL) THEN ROUND(adp.densidade, 2)
                WHEN (dv.fkdeposito IS NOT NULL) THEN ROUND(adv.densidade, 2)
                ELSE NULL
            END AS densidade,
            CASE
                WHEN (dv.fkdeposito IS NOT NULL) THEN ROUND(adv.pressao, 2)
                ELSE NULL
            END AS pressao,
            d.id AS idDeposito,
            d.capacidade AS capacidadeDeposito,
            d.numerodeposito AS numeroDeposito,
            d.tipodeposito AS tipoDeposito,
            CASE
                WHEN (dm.fkdeposito IS NOT NULL) THEN m.volume
                WHEN (dp.fkdeposito IS NOT NULL) THEN p.volume
                WHEN (dv.fkdeposito IS NOT NULL) THEN v.volume
                ELSE NULL
            END AS volumeConteudo,
            CASE
                WHEN (dm.fkdeposito IS NOT NULL) THEN m.id
                WHEN (dp.fkdeposito IS NOT NULL) THEN p.id
                WHEN (dv.fkdeposito IS NOT NULL) THEN v.id
                ELSE NULL
            END AS idConteudo
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
        WHERE d.id = :depositoId
    """, nativeQuery = true)
    public DadosInformacoesDepositos getDepositoWithIdWithInformations(@Param("depositoId") Long depositoId);
}
