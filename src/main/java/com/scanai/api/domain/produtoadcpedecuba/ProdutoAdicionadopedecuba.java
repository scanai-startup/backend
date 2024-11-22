package com.scanai.api.domain.produtoadcpedecuba;

import com.scanai.api.domain.produtoadcpedecuba.dto.DadosAtualizarProdutoAdicionadoPeDeCuba;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "tb_produto_adicionado_pedecuba")
@Entity(name = "tb_produto_adicionado_pedecuba")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProdutoAdicionadopedecuba {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkpedecuba;
    private String nome;
    private int quantidade;

    @Enumerated(EnumType.STRING)
    private UnidadeDeMedida unidade;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProdutoAdicionadopedecuba(Long fkpedecuba, String nome, int quantidade, UnidadeDeMedida unidade) {
        this.fkpedecuba = fkpedecuba;
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void atualizar(DadosAtualizarProdutoAdicionadoPeDeCuba dados) {
        this.fkpedecuba = dados.fkpedecuba();
        this.nome = dados.nome();
        this.quantidade = dados.quantidade();
        this.unidade = dados.unidade();
        this.updatedAt = LocalDateTime.now();
    }
}
