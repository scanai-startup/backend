package com.scanai.api.domain.depositovinho;

import com.scanai.api.domain.depositovinho.dto.DadosCadastroDepositoVinho;
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

    public Depositovinho(DadosCadastroDepositoVinho data) {
        this.fkfuncionario = data.fkfuncionario();
        this.datainicio = data.datainicio();
        this.fkdeposito = data.fkdeposito();
        this.fkvinho = data.fkvinho();
    }
}
