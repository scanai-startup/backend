package com.scanai.api.domain.entradamaterial;

import com.scanai.api.domain.entradamaterial.dto.DadosCadastroEntradaMaterial;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    private Date data;

    public EntradaMaterial(DadosCadastroEntradaMaterial dados) {
        this.fkmaterial = dados.fkmaterial();
        this.qttentrada = dados.qttentrada();
        this.valorunidade = dados.valorunidade();
        this.data = dados.data();
    }
}
