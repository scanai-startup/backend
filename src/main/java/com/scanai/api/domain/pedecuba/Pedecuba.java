package com.scanai.api.domain.pedecuba;


import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "tb_peDeCuba")
@Entity(name = "tb_peDeCuba")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedecuba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkfuncionario;
    private Long fkpedecuba;
    private LocalDate datafimfermentacao;
    private LocalDate datainicio;
    private float volume;
    private boolean valid;

    public Pedecuba(DadosCadastroPeDeCuba dados){
        this.fkfuncionario = dados.fkfuncionario();
        this.datainicio = dados.datainicio();
        this.volume = dados.volume();
        this.valid = true;
        this.fkpedecuba = dados.fkpedecuba();
    }

}
