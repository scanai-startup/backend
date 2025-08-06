package com.scanai.api.services;

import com.scanai.api.domain.enchimento.Enchimento;
import com.scanai.api.domain.enchimento.dto.DadosAtualizarEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosCadastroEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosDetalhamentoEnchimento;
import com.scanai.api.domain.enchimento.dto.DadosListagemEnchimento;

import java.util.List;

public interface EnchimentoServiceInterface {

    Enchimento register(DadosCadastroEnchimento dados);

    List<DadosListagemEnchimento> getAll();

    Enchimento getElement(Long id);

    void hardDelete(Long id);

    DadosDetalhamentoEnchimento update(DadosAtualizarEnchimento dados);
}
