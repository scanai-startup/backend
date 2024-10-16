package com.scanai.api.domain.produtoadcvinho;

import com.scanai.api.domain.produtoadcvinho.dto.DadosCadastroProdutoAdicionadoVinho;
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

    public ProdutoAdicionadovinho(DadosCadastroProdutoAdicionadoVinho dados){
        this.fkvinho = dados.fkvinho();
        this.nome = dados.nome();
        this.quantidade = dados.quantidade();
    }
}
