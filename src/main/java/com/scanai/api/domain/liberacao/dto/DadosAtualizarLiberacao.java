package com.scanai.api.domain.liberacao.dto;

import java.util.Date;

public record DadosAtualizarLiberacao(
        Long id,
        int qttproduzida,
      Date datainicio,
      Date datafim,
      int gfs,
      int fkrotulagem,
      int fkfuncionario) {
}
