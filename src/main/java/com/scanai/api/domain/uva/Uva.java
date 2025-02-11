package com.scanai.api.domain.uva;

import com.scanai.api.domain.uva.dto.DadosCadastroUva;
import com.scanai.api.domain.uva.dto.DadosAtualizarUva;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "tb_uva")
@Entity(name = "tb_uva")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Uva {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int qttcaixa;

    private boolean valid;
    private Date datachegada;
    private int numerotalao;
    private int sanidade;
    private int peso;
    private String so2;
    private int numerolote;
    private String tipovinho;
    private String casta;

    private Long fkfuncionario;
    private Long fkmostro;


    public Uva(DadosCadastroUva dados) {
        this.valid = true;
        this.qttcaixa = dados.qttcaixa();
        this.datachegada = dados.datachegada();
        this.numerotalao = dados.numerotalao();
        this.sanidade = dados.sanidade();
        this.peso = dados.peso();
        this.so2 = dados.so2();
        this.numerolote = dados.numerolote();
        this.tipovinho = dados.tipodevinho();
        this.casta = dados.casta();
        this.fkfuncionario = dados.fkfuncionario();
        this.fkmostro = dados.fkmostro();
    }

    public void update(DadosAtualizarUva dados){
        this.datachegada = dados.datachegada();
        this.numerotalao = dados.numerotalao();
        this.qttcaixa = dados.qttcaixa();
        this.sanidade = dados.sanidade();
        this.peso = dados.peso();
        this.so2 = dados.so2();
        this.numerolote = dados.numerolote();
        this.tipovinho = dados.tipodevinho();
        this.fkfuncionario = dados.fkfuncionario();
        this.fkmostro = dados.fkmostro();
    }

    public void softDelete() {
        this.valid = false;
    }

    public Boolean getValid() {
        return valid;
    }
    
    public void activate(){this.valid = true;}
}
