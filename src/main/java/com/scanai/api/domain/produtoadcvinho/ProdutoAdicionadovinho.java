package com.scanai.api.domain.produtoadcvinho;

import com.scanai.api.domain.produtoadcpedecuba.UnidadeDeMedida;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosAtualizarProdutoAdicionadoPeDeCuba;
import com.scanai.api.domain.produtoadcvinho.dto.DadosAtualizarProdutoAdicionadoVinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosCadastroProdutoAdicionadoVinho;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Enumerated(EnumType.STRING)
    private UnidadeDeMedida unidade;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProdutoAdicionadovinho(DadosCadastroProdutoAdicionadoVinho dados){
        this.fkvinho = dados.fkvinho();
        this.nome = dados.nome();
        this.quantidade = dados.quantidade();
        this.unidade = dados.unidade();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void atualizar(DadosAtualizarProdutoAdicionadoVinho dados) {
        this.fkvinho = dados.fkvinho();
        this.nome = dados.nome();
        this.quantidade = dados.quantidade();
        this.unidade = dados.unidade();
        this.updatedAt = LocalDateTime.now();
    }
}
