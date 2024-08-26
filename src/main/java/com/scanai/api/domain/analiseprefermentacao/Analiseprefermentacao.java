package com.scanai.api.domain.analiseprefermentacao;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_analise_pre_fermentacao")
@Entity(name = "tb_analise_pre_fermentacao")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Analiseprefermentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkfuncionario;
    private Long fkvinho;

    private float atotal;
    private float acucarRed;
    private int ph;
    private float so2l;
    private float ta;

    public Analiseprefermentacao(Long fkfuncionario, Long fkvinho, float atotal, float acucarRed, int ph, float so2l, float ta) {
        this.fkfuncionario = fkfuncionario;
        this.fkvinho = fkvinho;
        this.atotal = atotal;
        this.acucarRed = acucarRed;
        this.ph = ph;
        this.so2l = so2l;
        this.ta = ta;
    }
}
