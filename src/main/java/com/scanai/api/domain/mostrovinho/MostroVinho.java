package com.scanai.api.domain.mostrovinho;

import com.scanai.api.domain.mostrovinho.dto.DadosAtualizarMostroVinho;
import com.scanai.api.domain.mostrovinho.dto.DadosCadastroMostroVinho;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_mostrovinho")
@Table(name = "tb_mostrovinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MostroVinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fkmostro;
    private Long fkvinho;

    public MostroVinho(DadosCadastroMostroVinho dados){
        this.fkmostro = dados.fkmostro();
        this.fkvinho = dados.fkvinho();
    }
    public void atualizar(DadosAtualizarMostroVinho dados){
        this.fkmostro = dados.fkmostro();
        this.fkvinho = dados.fkvinho();
    }
}
