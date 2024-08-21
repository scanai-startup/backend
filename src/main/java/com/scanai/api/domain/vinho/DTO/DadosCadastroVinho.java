package com.scanai.api.domain.vinho.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroVinho(@Future Date datafimfermentacao,@NotNull float volume, @NotNull Long fkrotulo, @NotNull Long fkmostro, @NotNull Long fkpedecuba) {

}
