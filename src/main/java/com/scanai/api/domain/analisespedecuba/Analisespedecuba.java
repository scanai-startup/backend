package com.scanai.api.domain.analisespedecuba;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "tb_analise_diaria_pedecuba")
@Entity(name = "tb_analise_diaria_pedecuba")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Analisespedecuba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkpedecuba;
    private Long fkfuncionario;
    private float densidade;
    private LocalDate data;
    private int temperatura;

    public Analisespedecuba(Long fkpedecuba, Long fkfuncionario, float densidade, LocalDate data, int temperatura){
        this.fkpedecuba = fkpedecuba;
        this.fkfuncionario = fkfuncionario;
        this.densidade = densidade;
        this.data = data;
        this.temperatura = temperatura;
    }

}
