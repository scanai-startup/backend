package com.scanai.api.domain.vinho.DTO;

import com.scanai.api.domain.vinho.Vinho;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record DadosAtualizarVinho(Long id, LocalDate datafimfermentacao, float volume, Long fkrotulo, Long fkmostro, Long fkpedecuba) {
    public DadosAtualizarVinho(Vinho vinho) {
        this(vinho.getId(), vinho.getDatafimfermentacao(), vinho.getVolume(), vinho.getFkrotulo(), vinho.getFkmostro(), vinho.getFkpedecuba());
    }
}
