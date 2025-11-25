package com.scanai.api.services.implement;

import com.scanai.api.domain.tracking.DTO.DadosTrackingVinho;
import com.scanai.api.domain.vinho.Vinho;
import com.scanai.api.repositories.VinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingVinhoService {
    @Autowired
    VinhoService vinhoService;

    @Autowired
    VinhoRepository vinhoRepository;

    public DadosTrackingVinho trackingVinho(Long vinhoId){
        Vinho vinho = vinhoService.getElement(vinhoId);
        return new DadosTrackingVinho(vinho.getId(), vinho.getVolume(), vinho.getFkmostro(), vinho.getFkrotulo(), vinho.getFkpedecuba());

    }
}
