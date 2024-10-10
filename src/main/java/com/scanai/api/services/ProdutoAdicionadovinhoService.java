package com.scanai.api.services;

import com.scanai.api.domain.produtoadcvinho.ProdutoAdicionadovinho;
import com.scanai.api.domain.produtoadcvinho.dto.DadosCadastroProdutoAdicionadoVinho;
import org.springframework.stereotype.Service;

@Service
public class ProdutoAdicionadovinhoService {
    public ProdutoAdicionadovinho register(DadosCadastroProdutoAdicionadoVinho data) {
        return new ProdutoAdicionadovinho(data.fkvinho(), data.nome(), data.quantidade());
    }
}
