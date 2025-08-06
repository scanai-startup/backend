package com.scanai.api.services;

import com.scanai.api.domain.analisepedecuba.Analisepedecuba;
import com.scanai.api.domain.analisepedecuba.dto.DadosCadastroAnalisePeDeCuba;

public interface AnalisePeDeCubaServiceInterface {

    Analisepedecuba register(DadosCadastroAnalisePeDeCuba data);
}
