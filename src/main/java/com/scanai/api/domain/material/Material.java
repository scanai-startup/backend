package com.scanai.api.domain.material;

import com.scanai.api.domain.material.dto.DadosCadastroMaterial;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_material")
@Entity(name = "tb_material")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int quantidade;

    public Material(DadosCadastroMaterial dados){
        this.nome = dados.nome();
        this.quantidade = dados.quantidade();
    }

}
