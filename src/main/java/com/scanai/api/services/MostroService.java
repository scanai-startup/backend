package com.scanai.api.services;

import com.scanai.api.domain.mostro.Mostro;
import com.scanai.api.domain.mostro.dto.DadosCadastroMostro;
import org.springframework.stereotype.Service;

@Service
public class MostroService {
    public Mostro createMostro(DadosCadastroMostro data) {
        var newMostro = new Mostro(data.fkfuncionario(), data.volume());
        return newMostro;
    }

    public void softDelete(Mostro mostro) {
        mostro.setValid(false);
    }

    public void activate(Mostro mostro) {
        mostro.setValid(true);
    }
}
