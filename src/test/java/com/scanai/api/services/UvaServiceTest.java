package com.scanai.api.services;

import com.scanai.api.domain.uva.Uva;
import com.scanai.api.domain.uva.dto.DadosAtualizarUva;
import com.scanai.api.domain.uva.dto.DadosCadastroUva;
import com.scanai.api.domain.uva.dto.DadosListagemUva;
import com.scanai.api.repositories.UvaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UvaServiceTest {

    @Mock
    private UvaRepository repository; // Dependência simulada

    @InjectMocks
    private UvaService uvaService; // Classe sob teste

    @Test
    @DisplayName("Deve registrar uma nova uva com sucesso")
    void register_Cenario1() {
        // Arrange (Organizar)
        var dadosCadastro = new DadosCadastroUva(new Date(), 123, 50, 95, 1000, "SO2-OK", 202401, "Tinto", "Cabernet", 1L, null);
        var uvaSalva = new Uva(dadosCadastro);
        uvaSalva.setId(1L); // Simula o ID que seria gerado pelo banco

        // Simula o comportamento do repositório
        when(repository.save(any(Uva.class))).thenReturn(uvaSalva);

        // Act (Agir)
        Uva resultado = uvaService.register(dadosCadastro);

        // Assert (Verificar)
        assertNotNull(resultado);
        assertEquals(uvaSalva.getId(), resultado.getId());
        assertEquals("Cabernet", resultado.getCasta());
        verify(repository, times(1)).save(any(Uva.class)); // Verifica se o método save foi chamado uma vez
    }

    @Test
    @DisplayName("Deve listar todas as uvas que estão ativas (valid=true)")
    void listAllByValidTrue_Cenario1() {
        // Arrange
        Uva uva1 = new Uva(); // Supondo que o construtor padrão existe
        uva1.setValid(true);
        Uva uva2 = new Uva();
        uva2.setValid(true);
        List<Uva> listaDeUvasValidas = List.of(uva1, uva2);

        when(repository.findAllByValidTrue()).thenReturn(listaDeUvasValidas);

        // Act
        List<DadosListagemUva> resultado = uvaService.listAllByValidTrue();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAllByValidTrue();
    }

    @Test
    @DisplayName("Deve atualizar os dados de uma uva existente")
    void update_Cenario1() {
        // Arrange
        var idUva = 1L;
        var dadosAtualizacao = new DadosAtualizarUva(idUva, new Date(), 124, 55, 98, 1100, "SO2-AJUSTADO", 202402, "Branco", "Chardonnay", 2L, null);
        var uvaExistente = new Uva(new DadosCadastroUva(new Date(), 1, 1, 1, 1, "s", 1, "t", "c", 1L, null));
        uvaExistente.setId(idUva);

        when(repository.getReferenceById(idUva)).thenReturn(uvaExistente);

        // Act
        var dadosDetalhamento = uvaService.update(dadosAtualizacao);

        // Assert
        assertNotNull(dadosDetalhamento);
        assertEquals("Branco", dadosDetalhamento.tipodevinho());
        assertEquals(1100, dadosDetalhamento.peso());
        assertEquals(2L, uvaExistente.getFkfuncionario()); // Verifica se o objeto original foi alterado
        verify(repository, times(1)).getReferenceById(idUva);
    }

    @Test
    @DisplayName("Deve desativar uma uva (soft delete)")
    void softDelete_softDelete_Cenario1Cenario1() {
        // Arrange
        var idUva = 1L;
        var uvaAtiva = new Uva();
        uvaAtiva.setId(idUva);
        uvaAtiva.setValid(true);

        when(repository.getReferenceById(idUva)).thenReturn(uvaAtiva);

        // Act
        uvaService.softDelete(idUva);

        // Assert

        // O método save não é chamado, a modificação ocorre na instância em memória gerenciada pelo mock.
        // A asserção é feita diretamente no objeto retornado pelo mock.
        assertFalse(uvaAtiva.getValid());
        verify(repository, times(1)).getReferenceById(idUva);
    }

    @Test
    @DisplayName("Deve ativar uma uva que estava desativada")
    void activate_Cenario1() {
        // Arrange
        var idUva = 1L;
        var uvaInativa = new Uva();
        uvaInativa.setId(idUva);
        uvaInativa.setValid(false);

        when(repository.getReferenceById(idUva)).thenReturn(uvaInativa);

        // Act
        uvaService.activate(idUva);

        // Assert
        assertTrue(uvaInativa.getValid());
        verify(repository, times(1)).getReferenceById(idUva);
    }

    @Test
    @DisplayName("Deve remover uma uva permanentemente (hard delete)")
    void hardDelete_Cenario1(){
        // Arrange
        var idUva = 1L;

        // Act
        uvaService.hardDelete(idUva);

        // Assert
        verify(repository, times(1)).deleteById(idUva);

        verifyNoMoreInteractions(repository);
    }
}