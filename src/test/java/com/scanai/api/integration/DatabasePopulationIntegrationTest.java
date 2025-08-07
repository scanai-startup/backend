package com.scanai.api.integration;

import com.scanai.api.domain.deposito.dto.DadosCadastroDeposito;
import com.scanai.api.domain.funcionario.dto.RegisterDTO;
import com.scanai.api.domain.funcionario.dto.AuthenticationDTO;
import com.scanai.api.domain.funcionario.FuncionarioRole;
import com.scanai.api.domain.uva.dto.DadosCadastroUva;
import com.scanai.api.domain.material.dto.DadosCadastroMaterial;
import com.scanai.api.domain.lotematerial.dto.DadosCadastroLoteMaterial;
import com.scanai.api.domain.vinculodepositoremessas.dto.DadosCadastroVinculoDepositoRemessas;
import com.scanai.api.domain.analisediariamostro.dto.DadosCadastroAnaliseDiariaMostro;

import org.junit.jupiter.api.*;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.List;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DatabasePopulationIntegrationTest extends BaseIntegrationTest {

    // Variáveis para armazenar IDs criados durante os testes
    private static Long[] funcionarioIds = new Long[4]; // 3 funcionários + 1 admin
    private static Long[] depositoIds = new Long[8]; // 7 depósitos
    private static Long[] remessaIds = new Long[11]; // 10 remessas
    private static Long[] mostroIds = new Long[5]; // 4 mostros
    private static Long pedecubaId;
    private static Long vinhoId;
    private static Long rotuloId;
    private static Long[] materialIds = new Long[3]; // 2 materiais
    private static Long[] loteIds = new Long[3]; // 2 lotes
    private static String funcionarioToken;
    @Test
    @Order(1)
    @DisplayName("1. Setup - Obter tokens de autenticação")
    void obterTokensAutenticacao() throws Exception {
        // Assumindo que já existe um admin padrão no sistema com matricula: 123, senha: senha123
        
        // Login como funcionário/admin
        var loginData = new AuthenticationDTO("123", "senha123");
        
        MvcResult result = mockMvc.perform(post("/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginData)))
                .andExpect(status().isOk())
                .andReturn();
        
        funcionarioToken = extractTokenFromResponse(result);
        //adminToken = funcionarioToken; // Se for o mesmo usuário
        
        Assertions.assertNotNull(funcionarioToken);
        //Assertions.assertNotNull(adminToken);
        
        System.out.println("Tokens obtidos com sucesso!");
    }

    @Test
    @Order(2)
    @DisplayName("2. Criar funcionários")
    void criarFuncionarios() throws Exception {
        // Criar 3 funcionários seguindo o script Python
        String[] matriculas = {"11111", "22222", "33333"};
        String[] nomes = {"Cleiton", "Ricardo", "Aila"};
        String[] emails = {"cleiton@email.com", "ricardo@email.com", "aila@email.com"};
        
        for (int i = 0; i < 3; i++) {
            var funcionario = new RegisterDTO(
                matriculas[i], 
                "senha123", 
                FuncionarioRole.FUNCIONARIO, 
                nomes[i], 
                emails[i]
            );
            
            MvcResult result = performPost("/auth/register", funcionario, funcionarioToken);
            String response = result.getResponse().getContentAsString();
            //System.out.println("Resposta criação funcionário: " + response);
           // funcionarioIds[i + 1] = extractIdFromResponse(response);
        }
        
        System.out.println("Funcionários criados com sucesso!");
    }

    @Test
    @Order(3)
    @DisplayName("3. Criar depósitos")
    void criarDepositos() throws Exception {
        String[] tipos = {"AUT", "TAN", "TAN", "TAN", "AUT", "BAR", "BAR"};
        String[] numeros = {"0001", "0002", "0003", "0004", "0005", "0006", "0007"};
        float[] capacidades = {1000.0f, 1100.0f, 2000.0f, 1100.0f, 1200.0f, 1250.0f, 1500.0f};
        
        for (int i = 0; i < 7; i++) {
            var deposito = new DadosCadastroDeposito(
                tipos[i], numeros[i], capacidades[i]
            );
            System.out.println("funcionarioToken: "+funcionarioToken);
            MvcResult result = performPost("/deposito/register", deposito, funcionarioToken);
            String response = result.getResponse().getContentAsString();
            depositoIds[i + 1] = extractIdFromResponse(response);
            
            // Verificar se foi criado corretamente
            performGet("/deposito/getElement/" + depositoIds[i + 1], funcionarioToken);
        }
        
        System.out.println("Depósitos criados e verificados com sucesso!");
    }

    @Test
    @Order(4)
    @DisplayName("4. Criar remessas (uvas)")
    void criarRemessas() throws Exception {
        // Criar 10 remessas seguindo o script Python
        int[] numerosLote = {1, 1, 1, 2, 3, 4, 4, 4, 5, 5};
        int[] numerosTalao = {1, 2, 3, 1, 1, 1, 2, 3, 1, 2};
        
        for (int i = 0; i < 10; i++) {
            var uva = new DadosCadastroUva(
                new Date(),                    // datachegada
                numerosTalao[i],              // numerotalao
                20,                           // qttcaixa  
                95,                           // sanidade
                1000,                         // peso
                "SO2-OK",                     // so2
                numerosLote[i],               // numerolote
                "Tinto",                      // tipodevinho
                "Cabernet",                   // casta
                1L,                           // fkfuncionario
                null                          // fkmostro (será vinculado depois)
            );
            
            MvcResult result = performPost("/uva/register", uva, funcionarioToken);
            String response = result.getResponse().getContentAsString();
            remessaIds[i + 1] = extractIdFromResponse(response);
            
            // Verificar se foi criado corretamente
            performGet("/uva/getElement/" + remessaIds[i + 1], funcionarioToken);
        }
        
        System.out.println("Remessas criadas e verificadas com sucesso!");
    }

    @Test
    @Order(5)
    @DisplayName("5. Vincular remessas aos mostros")
    void vincularRemessasMostros() throws Exception {
        // Mostro 1: remessas 1,2 - volume 800 - depósito 1 - funcionário 1
        var vinculo1 = new DadosCadastroVinculoDepositoRemessas(
            Arrays.asList(remessaIds[1], remessaIds[2]),
            800.0f,
            depositoIds[1],
            1L
        );
        MvcResult result1 = performPost("/vinculoDepositoRemessas/register", vinculo1, funcionarioToken);
        mostroIds[1] = extractMostroIdFromVinculoResponse(result1.getResponse().getContentAsString());
        
        // Mostro 2: remessa 3 - volume 900 - depósito 2 - funcionário 2
        var vinculo2 = new DadosCadastroVinculoDepositoRemessas(
            Arrays.asList(remessaIds[3]),
            900.0f,
            depositoIds[2],
            2L
        );
        MvcResult result2 = performPost("/vinculoDepositoRemessas/register", vinculo2, funcionarioToken);
        mostroIds[2] = extractMostroIdFromVinculoResponse(result2.getResponse().getContentAsString());
        
        // Mostro 3: remessa 4 - volume 812 - depósito 3 - funcionário 2
        var vinculo3 = new DadosCadastroVinculoDepositoRemessas(
            Arrays.asList(remessaIds[4]),
            812.0f,
            depositoIds[3],
            2L
        );
        MvcResult result3 = performPost("/vinculoDepositoRemessas/register", vinculo3, funcionarioToken);
        mostroIds[3] = extractMostroIdFromVinculoResponse(result3.getResponse().getContentAsString());
        
        // Mostro 4: remessas 5,8 - volume 759 - depósito 5 - funcionário 1
        var vinculo4 = new DadosCadastroVinculoDepositoRemessas(
            Arrays.asList(remessaIds[5], remessaIds[8]),
            759.0f,
            depositoIds[5],
            1L
        );
        MvcResult result4 = performPost("/vinculoDepositoRemessas/register", vinculo4, funcionarioToken);
        mostroIds[4] = extractMostroIdFromVinculoResponse(result4.getResponse().getContentAsString());
        
        System.out.println("Remessas vinculadas aos mostros com sucesso!");
    }

    @Test
    @Order(6)
    @DisplayName("6. Criar análises diárias dos mostros")
    void criarAnalisesDiariasMostros() throws Exception {
        // Análises para mostros 1, 2 e 4
        Long[] mostrosParaAnalise = {mostroIds[1], mostroIds[2], mostroIds[4]};
        Long[] funcionarios = {1L, 2L, 2L};
        
        for (int i = 0; i < mostrosParaAnalise.length; i++) {
            var analise = new DadosCadastroAnaliseDiariaMostro(
                mostrosParaAnalise[i],    // fkmostro
                funcionarios[i],          // fkfuncionario
                1.055f,                   // densidade
                18.5f                     // temperatura
            );
            
            MvcResult result = performPost("/analiseDiariaMostro/register", analise, funcionarioToken);
            
            // Verificar se foi criado
            String response = result.getResponse().getContentAsString();
            Long analiseId = extractIdFromResponse(response);
            performGet("/analiseDiariaMostro/getElement/" + analiseId, funcionarioToken);
        }
        
        System.out.println("Análises diárias de mostros criadas com sucesso!");
    }

    @Test
    @Order(7)
    @DisplayName("7. Registrar materiais")
    void registrarMateriais() throws Exception {
        // Registrar materiais seguindo o script Python
        String[] nomesMateriais = {"rolha 15cm", "rolha 18cm"};
        
        for (int i = 0; i < nomesMateriais.length; i++) {
            var material = new DadosCadastroMaterial(nomesMateriais[i]);
            
            MvcResult result = performPost("/material/register", material, funcionarioToken);
            String response = result.getResponse().getContentAsString();
            materialIds[i + 1] = extractIdFromResponse(response);
        }
        
        System.out.println("Materiais registrados com sucesso!");
    }

    @Test
    @Order(8)
    @DisplayName("8. Registrar lotes")
    void registrarLotes() throws Exception {
        // Registrar lotes seguindo o script Python
        String[] fornecedores = {"Casas Bahia", "Americanas"};
        String[] numerosLote = {"123", "1"};
        
        for (int i = 0; i < fornecedores.length; i++) {
            var lote = new DadosCadastroLoteMaterial(
                materialIds[i + 1],     // fkmaterial
                fornecedores[i],        // fornecedor
                numerosLote[i]          // numerolote
            );
            
            MvcResult result = performPost("/loteMaterial/register", lote, funcionarioToken);
            String response = result.getResponse().getContentAsString();
            loteIds[i + 1] = extractIdFromResponse(response);
        }
        
        System.out.println("Lotes registrados com sucesso!");
    }

    // Métodos auxiliares para extrair IDs das respostas
    private Long extractIdFromResponse(String response) throws Exception {
        return objectMapper.readTree(response).get("id").asLong();
    }

    private Long extractMostroIdFromVinculoResponse(String response) throws Exception {
        return objectMapper.readTree(response).get("mostroId").asLong();
    }

    private Long extractPedecubaIdFromVinculoResponse(String response) throws Exception {
        return objectMapper.readTree(response).get("pedecubaId").asLong();
    }

    private Long extractVinhoIdFromVinculoResponse(String response) throws Exception {
        return objectMapper.readTree(response).get("vinhoId").asLong();
    }
}