package com.scanai.api.domain.mostro;

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

    public Mostro(LocalDate fimfermentacao, Long fkfuncionario, int volume){
        this.fimfermentacao = fimfermentacao;
        this.fkfuncionario = fkfuncionario;
        this.volume = volume;
        this.valid = true;
    }

}
