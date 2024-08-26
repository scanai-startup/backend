package com.scanai.api.domain.depositopedecuba;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "tb_deposito_pedecuba")
@Entity(name = "tb_deposito_pedecuba")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Depositopedecuba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkpedecuba;
    private Long fkdeposito;
    private LocalDate datainicio;
    private LocalDate datafim;
    private Long fkfuncionario;

    public Depositopedecuba(Long fkpedecuba, Long fkdeposito, LocalDate datainicio, Long fkfuncionario) {
        this.fkpedecuba = fkpedecuba;
        this.fkdeposito = fkdeposito;
        this.datainicio = datainicio;
        this.fkfuncionario = fkfuncionario;
    }
}
