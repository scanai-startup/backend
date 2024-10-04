package com.scanai.api.domain.liberacao;

import com.scanai.api.domain.liberacao.dto.DadosAtualizarLiberacao;
import com.scanai.api.domain.liberacao.dto.DadosCadastroLiberacao;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "Liberacao")
@Entity(name = "Liberacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Liberacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int qttProduzida;
    private Date dataInicio;
    private Date dataFim;
    private int gfs	;

    private Long fkrotulagem;
    private Long fkfuncionario;


    public Liberacao(DadosCadastroLiberacao dados) {

    }

    public void atualizar(DadosAtualizarLiberacao dados){

    }

}
