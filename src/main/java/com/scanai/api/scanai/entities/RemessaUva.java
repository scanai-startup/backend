package com.scanai.api.scanai.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "REMESSA_UVA")
@Entity(name = "RemessaUva")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class RemessaUva {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isValid;
    private LocalDateTime dataChegada_remessaUva;
    private int numeroTalao_remessaUva;
    private int sanidade_remessaUva;
    private int peso_remessaUva;
    private String so2_remessaUva;
    private int numeroLote_remessaUva;
    private String tipoDeVinho_remessaUva;
    private String casta_remessaUva;
}
