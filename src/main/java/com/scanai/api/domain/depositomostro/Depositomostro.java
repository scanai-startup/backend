package com.scanai.api.domain.depositomostro;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "tb_deposito_mostro")
@Entity(name = "tb_deposito_mostro")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Depositomostro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkmostro;
    private Long fkdeposito;
    private LocalDate datainicio;
    private LocalDate datafim;
    private Long fkfuncionario;

    public Depositomostro(Long fkmostro, Long fkdeposito, LocalDate datainicio, Long fkfuncionario) {
        this.fkmostro = fkmostro;
        this.fkdeposito = fkdeposito;
        this.datainicio = datainicio;
        this.fkfuncionario = fkfuncionario;
    }
}
