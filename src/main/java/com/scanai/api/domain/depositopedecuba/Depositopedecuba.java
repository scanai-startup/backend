package com.scanai.api.domain.depositopedecuba;

import com.scanai.api.domain.depositopedecuba.dto.DadosCadastroDepositoPeDeCuba;
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

    public Depositopedecuba(DadosCadastroDepositoPeDeCuba data) {
        this.fkpedecuba = data.fkpedecuba();
        this.fkdeposito = data.fkdeposito();
        this.datainicio = data.datainicio();
        this.fkfuncionario = data.fkfuncionario();
    }
}
