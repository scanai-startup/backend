package com.scanai.api.services.implement;

import com.scanai.api.domain.rotulo.DTO.DadosDetalhamentoRotulo;
import com.scanai.api.domain.rotulo.Rotulo;
import com.scanai.api.domain.tracking.DTO.DadosTrackingRotulo;
import com.scanai.api.domain.tracking.DTO.DadosTrackingVinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingRotuloService {
    @Autowired
    RotuloService rotuloService;

    public DadosTrackingRotulo trackingRotulo(Long idRotulo){
        DadosDetalhamentoRotulo dadosDetalhamentoRotulo = rotuloService.getElement(idRotulo);
        return new DadosTrackingRotulo(dadosDetalhamentoRotulo.id());
    }
}
