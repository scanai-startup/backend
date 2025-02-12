package com.scanai.api.domain.entradamaterial.dto;

import com.scanai.api.domain.entradamaterial.EntradaMaterial;

import java.time.LocalDate;

public record DadosListagemEntradaMaterial(Long id, Long fklotematerial, int qttentrada, LocalDate dataentrada) {
    public DadosListagemEntradaMaterial (EntradaMaterial dados) {
        this(
                dados.getId(),
                dados.getFklotematerial(),
                dados.getQttentrada(),
                dados.getDataentrada()
        );
    }
}
