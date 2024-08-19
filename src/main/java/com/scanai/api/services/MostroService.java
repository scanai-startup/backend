package com.scanai.api.services;

import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.RegisterMostroDTO;
import org.springframework.stereotype.Service;

@Service
public class MostroService {
    public Mostro createMostro(RegisterMostroDTO data) {
        var newMostro = new Mostro(data.fimfermentacao(), data.fkfuncionario(), data.volume());
        return newMostro;
    }

    public void invalidadeMostro(Mostro mostro) {
        mostro.setValid(false);
    }

    public void validateMostro(Mostro mostro) {
        mostro.setValid(true);
    }
}
