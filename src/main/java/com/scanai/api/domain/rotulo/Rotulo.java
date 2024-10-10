package com.scanai.api.domain.rotulo;

import com.scanai.api.domain.rotulo.DTO.DadosCadastroRotulo;
import com.scanai.api.domain.rotulo.DTO.DadosAtualizarRotulo;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_rotulo")
@Entity(name = "tb_rotulo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Rotulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean valid;
    private String nome;
    private String tipo;

    public Rotulo(DadosCadastroRotulo dados) {
        this.valid = true;
        this.nome = dados.nome();
        this.tipo = dados.tipo();
    }

    public void update(DadosAtualizarRotulo dados){
        this.nome = dados.nome();
        this.tipo = dados.tipo();
    }


}
