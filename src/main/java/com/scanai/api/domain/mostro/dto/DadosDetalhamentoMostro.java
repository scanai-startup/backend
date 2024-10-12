package com.scanai.api.domain.mostro.dto;

import com.scanai.api.domain.mostro.Mostro;

public record DadosDetalhamentoMostro(Long id, Long funcionario, float volume, boolean valid) {
    public DadosDetalhamentoMostro(Mostro mostro) {
        this(
                mostro.getId(),
                mostro.getFkfuncionario(),
                mostro.getVolume(),
                mostro.isValid()
        );
    }
}
