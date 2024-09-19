package com.scanai.api.domain.analisediariavinho;

import com.scanai.api.domain.analisediariavinho.dto.DadosAtualizarAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosCadastroAnaliseDiariaVinho;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name="tb_analisediariavinho")
@Table(name="tb_analisediariavinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnaliseDiariaVinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fkfuncionario;
    private Long fkvinho;
    private float densidade;
    private Date data;
    private float temperatura;
    private float pressao;

    public AnaliseDiariaVinho(DadosCadastroAnaliseDiariaVinho dados){
        this.fkvinho = dados.fkvinho();
        this.fkfuncionario = dados.fkfuncionario();
        this.densidade = dados.densidade();
        this.data = dados.data();
        this.temperatura = dados.temperatura();
        this.pressao = dados.pressao();
    }
    public void atualizar(DadosAtualizarAnaliseDiariaVinho dados){
        this.fkvinho = dados.fkvinho();
        this.fkfuncionario = dados.fkfuncionario();
        this.densidade = dados.densidade();
        this.data = dados.data();
        this.temperatura = dados.temperatura();
        this.pressao = dados.pressao();
    }

}
