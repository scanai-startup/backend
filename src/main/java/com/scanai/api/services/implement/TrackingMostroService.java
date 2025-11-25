package com.scanai.api.services.implement;

import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.uva.dto.DadosListagemUva;
import com.scanai.api.repositories.MostroRepository;
import com.scanai.api.domain.tracking.DTO.DadosTrackingMostro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TrackingMostroService {
    @Autowired
    TrackingUvaService trackingUvaService;
    @Autowired
    MostroRepository mostroRepository;
    @Autowired
    AnaliseDiariaMostroService analiseDiariaMostroService;

    public DadosTrackingMostro TrackingMostro(Long id){
        Mostro mostro = mostroRepository.getReferenceById(id);
        List<DadosListagemUva> uvas = trackingUvaService.dadosListagemUvaList(mostro.getId());

        Long mostro1Id = null;
        Float mostro1Volume = null;
        Long mostro2Id = null;
        Float mostro2Volume = null;

        if (mostro.getFkmostro1() != null) {
            Mostro mostro1 = mostroRepository.getReferenceById(mostro.getFkmostro1());
            mostro1Id = mostro1.getId();
            mostro1Volume = mostro1.getVolume();
        }

        if (mostro.getFkmostro2() != null) {
            Mostro mostro2 = mostroRepository.getReferenceById(mostro.getFkmostro2());
            mostro2Id = mostro2.getId();
            mostro2Volume = mostro2.getVolume();
        }

        return new DadosTrackingMostro(mostro.getId(), mostro.getVolume(), analiseDiariaMostroService.getElement(mostro.getId()), mostro1Id, mostro1Volume, mostro2Id, mostro2Volume, uvas);

    }
}
