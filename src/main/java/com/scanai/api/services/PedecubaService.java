package com.scanai.api.services;

import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;
import org.springframework.stereotype.Service;

@Service
public class PedecubaService {
    public Pedecuba register(DadosCadastroPeDeCuba data) {
        return new Pedecuba(data.fkfuncionario(), data.datainicio(), data.volume());
    }

    public void softDelete(Pedecuba pedecuba) {
        pedecuba.setValid(false);
    }

    public void activate(Pedecuba pedecuba) {
        pedecuba.setValid(true);
    }
}
