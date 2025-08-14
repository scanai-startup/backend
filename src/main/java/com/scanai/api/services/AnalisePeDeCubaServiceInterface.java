package com.scanai.api.services;

import com.scanai.api.domain.analisepedecuba.dto.DadosCadastroAnalisePeDeCuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosDetalhamentoAnalisePeDeCuba;

public interface AnalisePeDeCubaServiceInterface {

    DadosDetalhamentoAnalisePeDeCuba register(DadosCadastroAnalisePeDeCuba data);
}
