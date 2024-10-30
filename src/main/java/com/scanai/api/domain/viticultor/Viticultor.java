package com.scanai.api.domain.viticultor;

import com.scanai.api.domain.viticultor.DTO.DadosAtualizarViticultor;
import com.scanai.api.domain.viticultor.DTO.DadosCadastroViticultor;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_viticultor")
@Entity(name = "tb_viticultor")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Viticultor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String cpf;
    String nome;
    public Viticultor(DadosCadastroViticultor dados) {
        this.cpf = dados.cpf();
        this.nome = dados.nome();
    }

    public void atualizar(DadosAtualizarViticultor dados) {
        this.cpf = dados.cpf();
        this.nome = dados.nome();
    }

}
