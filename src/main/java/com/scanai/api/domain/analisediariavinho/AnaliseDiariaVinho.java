package com.scanai.api.domain.analisediariavinho;

import com.scanai.api.domain.analisediariavinho.dto.DadosAtualizarAnaliseDiariaVinho;
import com.scanai.api.domain.analisediariavinho.dto.DadosCadastroAnaliseDiariaVinho;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name="tb_analise_diaria_vinho")
@Table(name="tb_analise_diaria_vinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
