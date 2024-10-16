package com.scanai.api.domain.produtoadcpedecuba;

import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
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

    public ProdutoAdicionadopedecuba(DadosCadastroProdutoAdicionadoPeDeCuba dados){
        this.fkpedecuba = dados.fkpedecuba();
        this.nome = dados.nome();
        this.quantidade = dados.quantidade();
    }
}
