package com.scanai.api.domain.viticultor;

import com.scanai.api.domain.viticultor.DTO.DadosAtualizarViticultor;
import com.scanai.api.domain.viticultor.DTO.DadosCadatroViticultor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "tb_viticultor")
@Entity(name = "tb_viticultor")
@Getter
@Setter
public class Viticultor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String cpf;
    String nome;
    public Viticultor(DadosCadatroViticultor dados) {
        this.cpf = dados.cpf();
        this.nome = dados.nome();
    }

    public void atualizar(DadosAtualizarViticultor dados) {
        this.cpf = dados.cpf();
        this.nome = dados.nome();
    }

}
