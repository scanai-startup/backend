package com.scanai.api.domain.vinho;

import com.scanai.api.domain.vinho.DTO.DadosAtualizarVinho;
import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "tb_vinho")
@Entity(name = "tb_vinho")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Vinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean valid;
    private Date datafimfermentacao;
    private float volume;

    public Vinho(DadosCadastroVinho dados) {
        this.valid = true;
        this.datafimfermentacao = dados.datafimfermentacao();
        this.volume = dados.volume();
    }

    public void atualizar(DadosAtualizarVinho dados){
        this.datafimfermentacao = dados.datafimfermentacao();
        this.volume = dados.volume();
    }

    public void inativar() {
        this.valid = false;
    }

    public Boolean getValid() {
        return valid;
    }

}
