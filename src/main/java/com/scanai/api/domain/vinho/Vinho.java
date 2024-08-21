package com.scanai.api.domain.vinho;

import com.scanai.api.domain.vinho.DTO.DadosAtualizarVinho;
import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    // Chaves estrangeiras
    private Long fkrotulo;
    private Long fkmostro;
    private Long fkpedecuba;

    public Vinho(DadosCadastroVinho dados) {
        this.valid = true;
        this.datafimfermentacao = dados.datafimfermentacao();
        this.volume = dados.volume();
        this.fkrotulo = dados.fkpedecuba();
        this.fkmostro = dados.fkmostro();
        this.fkpedecuba = dados.fkpedecuba();
    }

    public void atualizar(DadosAtualizarVinho dados){
        this.datafimfermentacao = dados.datafimfermentacao();
        this.volume = dados.volume();
        this.fkrotulo = dados.fkpedecuba();
        this.fkmostro = dados.fkmostro();
        this.fkpedecuba = dados.fkpedecuba();
    }

    public void inativar() {
        this.valid = false;
    }

    public Boolean getValid() {
        return valid;
    }

}
