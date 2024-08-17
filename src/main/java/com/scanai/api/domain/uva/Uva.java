package com.scanai.api.domain.uva;

import com.scanai.api.domain.uva.dto.DadosCadastroUva;
import com.scanai.api.domain.uva.dto.DadosAtualizarUva;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "uva")
@Entity(name = "uva")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Uva {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean valid;
    private Date datachegada;
    private int numerotalao;
    private int sanidade;
    private int peso;
    private String so2;
    private int numerolote;
    private String tipovinho;
    private String casta;

    public Uva(DadosCadastroUva dados) {
        this.valid = true;
        this.datachegada = dados.datachegada();
        this.numerotalao = dados.numerotalao();
        this.sanidade = dados.sanidade();
        this.peso = dados.peso();
        this.so2 = dados.so2();
        this.numerolote = dados.numerolote();
        this.tipovinho = dados.tipodevinho();
        this.casta = dados.casta();
    }

    public void atualizar(DadosAtualizarUva dados){
        this.datachegada = dados.datachegada();
        this.numerotalao = dados.numerotalao();
        this.sanidade = dados.sanidade();
        this.peso = dados.peso();
        this.so2 = dados.so2();
        this.numerolote = dados.numerolote();
        this.tipovinho = dados.tipodevinho();
        this.datachegada = dados.datachegada();
    }

    public void inativar() {
        this.valid = false;
    }

    public Boolean getValid() {
        return valid;
    }
}
