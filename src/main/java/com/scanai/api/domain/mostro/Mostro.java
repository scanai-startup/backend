package com.scanai.api.domain.mostro;

import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "tb_mostro")
@Entity(name = "tb_mostro")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Mostro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fimfermentacao;
    private Long fkfuncionario;
    private Float volume;
    private boolean valid;

    private Long fkmostro1;
    private Long fkmostro2;

    public Mostro(DadosCadastroMostro data){
        this.fkfuncionario = data.fkfuncionario();
        this.volume = data.volume();
        this.valid = true;
        this.fkmostro1 = data.fkmostro1();
        this.fkmostro2 = data.fkmostro2();
    }

}
