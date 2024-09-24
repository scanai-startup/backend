package com.scanai.api.domain.lotematerial;

import com.scanai.api.domain.lotematerial.dto.RegisterLotematerialDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_lotematerial")
@Entity(name = "tb_lotematerial")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Lotematerial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fornecedor;
    private String numerolote;
    private Long fkmaterial;

    public Lotematerial(RegisterLotematerialDTO dados) {
        this.fornecedor = dados.fornecedor();
        this.numerolote = dados.numerolote();
        this.fkmaterial = dados.fkmaterial();
    }
}
