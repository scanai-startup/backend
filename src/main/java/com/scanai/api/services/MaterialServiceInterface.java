package com.scanai.api.services;

import com.scanai.api.domain.material.Material;
import com.scanai.api.domain.material.dto.DadosCadastroMaterial;
import com.scanai.api.domain.material.dto.DadosDetalhamentoMaterial;
import com.scanai.api.domain.material.dto.DadosListagemMaterial;

import java.util.List;

public interface MaterialServiceInterface {

    DadosDetalhamentoMaterial register(DadosCadastroMaterial dados);

    List<DadosListagemMaterial> getAll();
}
