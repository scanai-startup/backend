package com.scanai.api.domain.produtoadcvinho;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_produto_adicionado_vinho")
@Entity(name = "tb_produto_adicionado_vinho")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProdutoAdicionadovinho {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkvinho;
    private String nome;
    private int quantidade;

    public ProdutoAdicionadovinho(Long fkvinho, String nome, int quantidade){
        this.fkvinho = fkvinho;
        this.nome = nome;
        this.quantidade = quantidade;
    }
}
