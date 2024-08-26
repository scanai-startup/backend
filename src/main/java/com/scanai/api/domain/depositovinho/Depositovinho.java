package com.scanai.api.domain.depositovinho;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "tb_deposito_vinho")
@Entity(name = "tb_deposito_vinho")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Depositovinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkvinho;
    private Long fkdeposito;
    private LocalDate datainicio;
    private LocalDate datafim;
    private Long fkfuncionario;

    public Depositovinho(Long fkfuncionario, LocalDate datainicio, Long fkdeposito, Long fkvinho) {
        this.fkfuncionario = fkfuncionario;
        this.datainicio = datainicio;
        this.fkdeposito = fkdeposito;
        this.fkvinho = fkvinho;
    }
}
