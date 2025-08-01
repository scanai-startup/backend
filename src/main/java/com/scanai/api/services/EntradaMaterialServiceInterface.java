package com.scanai.api.services;

import com.scanai.api.domain.entradamaterial.EntradaMaterial;
import com.scanai.api.domain.entradamaterial.dto.DadosCadastroEntradaMaterial;
import com.scanai.api.domain.entradamaterial.dto.DadosListagemEntradaMaterial;

import java.util.List;

public interface EntradaMaterialServiceInterface {

    EntradaMaterial register(DadosCadastroEntradaMaterial data);

    List<DadosListagemEntradaMaterial> getAll();
}
