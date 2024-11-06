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
    private int volume;
    private boolean valid;

    public Mostro(DadosCadastroMostro data){
        this.fkfuncionario = data.fkfuncionario();
        this.volume = data.volume();
        this.valid = true;
    }

}
