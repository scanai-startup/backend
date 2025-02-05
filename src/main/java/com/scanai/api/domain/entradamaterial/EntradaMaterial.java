package com.scanai.api.domain.entradamaterial;

import com.scanai.api.domain.entradamaterial.dto.DadosCadastroEntradaMaterial;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name="tb_entradamaterial")
@Table(name="tb_entradamaterial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EntradaMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkmaterial;
    private int qttentrada;
    private float valorunidade;
    private LocalDate dataentrada;
    private Long fklotematerial;

    public EntradaMaterial(DadosCadastroEntradaMaterial dados) {
        this.fkmaterial = dados.fkmaterial();
        this.qttentrada = dados.qttentrada();
        this.valorunidade = dados.valorunidade();
        this.dataentrada = dados.dataentrada()  ;
        this.fklotematerial = dados.fklotematerial();
    }
}
