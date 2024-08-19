package com.scanai.api.services;

import com.scanai.api.domain.pedecuba.Pedecuba;
import com.scanai.api.domain.pedecuba.dto.RegisterPedecubaDTO;
import org.springframework.stereotype.Service;

@Service
public class PedecubaService {
    public Pedecuba createPedecuba(RegisterPedecubaDTO data) {
        return new Pedecuba(data.fkfuncionario(), data.datainicio(), data.volume());
    }

    public void invalidadePedecuba(Pedecuba pedecuba) {
        pedecuba.setValid(false);
    }

    public void validadePedecuba(Pedecuba pedecuba) {
        pedecuba.setValid(true);
    }
}
