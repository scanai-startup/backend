package com.scanai.api.domain.liberacao;

import com.scanai.api.domain.liberacao.dto.DadosCadastroLiberacao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "tb_liberacao")
@Entity(name = "tb_liberacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Liberacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int qttproduzida;
    private LocalDate datainicio;
    private LocalDate datafim;
    private int gfs	;

    private Long fkrotulagem;
    private Long fkfuncionario;

    public Liberacao(DadosCadastroLiberacao dados) {
        this.qttproduzida = dados.qttproduzida();
        this.datainicio = dados.datainicio();
        this.datafim = dados.datafim();
        this.gfs = dados.gfs();
        this.fkrotulagem = dados.fkrotulagem();
        this.fkfuncionario = dados.fkfuncionario();
    }
}
