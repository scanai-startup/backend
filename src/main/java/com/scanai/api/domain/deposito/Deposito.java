package com.scanai.api.domain.deposito;

import com.scanai.api.domain.deposito.dto.DadosCadastroDeposito;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_deposito")
@Entity(name = "tb_deposito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipodeposito;
    private String numerodeposito;
    private boolean valid;

    public Deposito(DadosCadastroDeposito data){
        this.tipodeposito = data.tipodeposito();
        this.numerodeposito = data.numerodeposito();
        this.valid = true; //define sempre ativo ao criar o objeto;
    }

}
