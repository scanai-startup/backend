package com.scanai.api.domain.analisepedecuba;

import com.scanai.api.domain.analisepedecuba.dto.DadosCadastroAnalisePeDeCuba;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "tb_analise_diaria_pedecuba")
@Entity(name = "tb_analise_diaria_pedecuba")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AnalisePeDeCubaService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkpedecuba;
    private Long fkfuncionario;
    private Float densidade;
    private LocalDateTime data;
    private Float temperatura;

    public AnalisePeDeCubaService(DadosCadastroAnalisePeDeCuba data){
        this.fkpedecuba = data.fkpedecuba();
        this.fkfuncionario = data.fkfuncionario();
        this.densidade = data.densidade();
        this.data = LocalDateTime.now();
        this.temperatura = data.temperatura();
    }

}
