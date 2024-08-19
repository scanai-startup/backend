package com.scanai.api.domain.pedecuba;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "tb_peDeCuba")
@Entity(name = "tb_peDeCuba")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedecuba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkfuncionario;
    private LocalDate datafimfermentacao;
    private LocalDate datainicio;
    private int volume;
    private boolean valid;

    public Pedecuba(Long fkfuncionario, LocalDate datainicio, int volume){
        this.fkfuncionario = fkfuncionario;
        this.datainicio = datainicio;
        this.volume = volume;
        this.valid = true;
    }
}
