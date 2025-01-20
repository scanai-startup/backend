package com.scanai.api.domain.analisepedecuba;

import com.scanai.api.domain.analisepedecuba.dto.DadosCadastroAnalisePeDeCuba;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "tb_analise_diaria_pedecuba")
@Entity(name = "tb_analise_diaria_pedecuba")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Analisepedecuba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkpedecuba;
    private Long fkfuncionario;
    private float densidade;
    private LocalDateTime data;
    private int temperatura;

    public Analisepedecuba(DadosCadastroAnalisePeDeCuba data){
        this.fkpedecuba = data.fkpedecuba();
        this.fkfuncionario = data.fkfuncionario();
        this.densidade = data.densidade();
        this.data = LocalDateTime.now();
        this.temperatura = data.temperatura();
    }

}
