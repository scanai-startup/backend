package com.scanai.api.domain.analiseprefermentacao;


import com.scanai.api.domain.analiseprefermentacao.dto.DadosCadastroAnalisePreFermetacao;
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

    public Analiseprefermentacao(DadosCadastroAnalisePreFermetacao data) {
        this.fkfuncionario = data.fkfuncionario();
        this.fkvinho = data.fkvinho();
        this.atotal = data.atotal();
        this.acucarRed = data.acucarRed();
        this.ph = data.ph();
        this.so2l = data.so2l();
        this.ta = data.ta();
    }
}
