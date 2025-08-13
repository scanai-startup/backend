package com.scanai.api.services;

import com.scanai.api.domain.lotematerial.Lotematerial;
import com.scanai.api.domain.lotematerial.dto.DadosCadastroLoteMaterial;
import com.scanai.api.domain.lotematerial.dto.DadosDetalhamentoLoteMaterial;

import java.util.List;

public interface LoteMaterialServiceInterface {

    DadosDetalhamentoLoteMaterial register(DadosCadastroLoteMaterial dados);

    List<DadosDetalhamentoLoteMaterial> getAll();
}
