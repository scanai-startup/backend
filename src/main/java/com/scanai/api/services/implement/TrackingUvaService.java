package com.scanai.api.services.implement;

import com.scanai.api.domain.uva.dto.DadosListagemUva;
import com.scanai.api.repositories.UvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackingUvaService {
    @Autowired
    UvaRepository uvaRepository;

    public List<DadosListagemUva> dadosListagemUvaList(Long mostroId){
        return uvaRepository.findAllByFkmostro(mostroId).stream().map(DadosListagemUva::new).toList();
    }

}
