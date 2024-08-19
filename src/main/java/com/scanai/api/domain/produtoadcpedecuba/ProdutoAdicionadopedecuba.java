package com.scanai.api.domain.produtoadcpedecuba;

import jakarta.persistence.*;
import lombok.*;

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

    public ProdutoAdicionadopedecuba(Long fkpedecuba, String nome, int quantidade){
        this.fkpedecuba = fkpedecuba;
        this.nome = nome;
        this.quantidade = quantidade;
    }
}
