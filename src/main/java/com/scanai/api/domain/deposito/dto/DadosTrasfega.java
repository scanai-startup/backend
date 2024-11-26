package com.scanai.api.domain.deposito.dto;

public record DadosTrasfega(
        Long depositoorigemId,
        Long depositodestinoId,
        Long conteudoId,
        String conteudo
){
}
