package com.scanai.api.domain.depositomostro;

import com.scanai.api.domain.depositomostro.dto.DadosCadastroDepositoMostro;
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
public class DepositoMostro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkmostro;
    private Long fkdeposito;
    private LocalDate datainicio;
    private LocalDate datafim;
    private Long fkfuncionario;

    public DepositoMostro(DadosCadastroDepositoMostro data) {
        this.fkmostro = data.fkmostro();
        this.fkdeposito = data.fkdeposito();
        this.datainicio = data.datainicio();
        this.fkfuncionario = data.fkfuncionario();
    }
}
