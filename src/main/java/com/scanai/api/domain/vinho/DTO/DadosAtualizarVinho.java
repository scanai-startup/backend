package com.scanai.api.domain.vinho.DTO;

import java.util.Date;

public record DadosAtualizarVinho(Long id, Date datafimfermentacao, float volume, Long fkrotulo, Long fkmostro, Long fkpedecuba) {
}
