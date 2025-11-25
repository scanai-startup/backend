package com.scanai.api.domain.tracking.DTO;

import com.scanai.api.domain.analisediariamostro.dto.DadosDetalhamentoAnaliseDiariaMostro;
import com.scanai.api.domain.analisediariamostro.dto.DadosListagemAnaliseDiariaMostro;
import com.scanai.api.domain.uva.dto.DadosListagemUva;

import java.util.List;

public record DadosTrackingMostro(Long id, Float volume, DadosDetalhamentoAnaliseDiariaMostro analiseDiariaMostro, Long mosto1Id, Float volumeMosto1, Long mosto2Id, Float volumeMosto2, List<DadosListagemUva> uvas) {

}
