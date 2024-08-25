package com.scanai.api.services;

import com.scanai.api.domain.produtoadcvinho.ProdutoAdicionadovinho;
import com.scanai.api.domain.produtoadcvinho.dto.RegisterProdutoadcvinhoDTO;
import org.springframework.stereotype.Service;

@Service
public class ProdutoAdicionadovinhoService {
    public ProdutoAdicionadovinho createProdutoadcvinho(RegisterProdutoadcvinhoDTO data) {
        return new ProdutoAdicionadovinho(data.fkvinho(), data.nome(), data.quantidade());
    }
}
