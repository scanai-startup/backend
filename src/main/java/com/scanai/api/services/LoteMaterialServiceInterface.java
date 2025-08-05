package com.scanai.api.services;

import com.scanai.api.domain.lotematerial.Lotematerial;
import com.scanai.api.domain.lotematerial.dto.DadosCadastroLoteMaterial;

import java.util.List;

public interface LoteMaterialServiceInterface {

    Lotematerial register(DadosCadastroLoteMaterial dados);

    List<Lotematerial> getAll();
}
