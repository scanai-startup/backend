package com.scanai.api.domain.analisediariamostro;

import com.scanai.api.domain.analisediariamostro.dto.DadosAtualizarAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosCadastroAnaliseDiariaMostro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name="tb_analisediariamostro")
@Table(name="tb_analisediariamostro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnaliseDiariaMostro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fkfuncionario;
    private Long fkmostro;
    private float densidade;
    private float temperatura;
    private Date data;

    public AnaliseDiariaMostro(DadosCadastroAnaliseDiariaMostro dados){
        this.fkmostro = dados.fkmostro();
        this.fkfuncionario = dados.fkfuncionario();
        this.densidade = dados.densidade();
        this.temperatura = dados.temperatura();
        this.data = dados.data();
    }
    public void atualizar(DadosAtualizarAnaliseDiariaMostro dados){
        this.fkmostro = dados.fkmostro();
        this.fkfuncionario = dados.fkfuncionario();
        this.densidade = dados.densidade();
        this.temperatura = dados.temperatura();
        this.data = dados.data();
    }

}
