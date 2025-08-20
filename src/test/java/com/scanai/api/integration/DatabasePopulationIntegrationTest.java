package com.scanai.api.integration;

import com.scanai.api.domain.analisediariavinho.dto.DadosCadastroAnaliseDiariaVinho;
import com.scanai.api.domain.deposito.dto.DadosCadastroDeposito;
import com.scanai.api.domain.enchimento.dto.DadosCadastroEnchimento;
import com.scanai.api.domain.entradamaterial.dto.DadosCadastroEntradaMaterial;
import com.scanai.api.domain.funcionario.dto.RegisterDTO;
import com.scanai.api.domain.funcionario.dto.AuthenticationDTO;
import com.scanai.api.domain.funcionario.FuncionarioRole;
import com.scanai.api.domain.liberacao.dto.DadosCadastroLiberacao;
import com.scanai.api.domain.pedecuba.dto.DadosCadastroPeDeCuba;
import com.scanai.api.domain.produtoadcpedecuba.dto.DadosCadastroProdutoAdicionadoPeDeCuba;
import com.scanai.api.domain.rotulagem.dto.DadosCadastroRotulagem;
import com.scanai.api.domain.rotulo.DTO.DadosCadastroRotulo;
import com.scanai.api.domain.uva.dto.DadosCadastroUva;
import com.scanai.api.domain.material.dto.DadosCadastroMaterial;
import com.scanai.api.domain.lotematerial.dto.DadosCadastroLoteMaterial;
import com.scanai.api.domain.vinculodepositoremessas.dto.DadosCadastroVinculoDepositoRemessas;
import com.scanai.api.domain.analisediariamostro.dto.DadosCadastroAnaliseDiariaMostro;

import com.scanai.api.domain.vinho.DTO.DadosCadastroVinho;
import org.junit.jupiter.api.*;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Tag("manual")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DatabasePopulationIntegrationTest extends BaseIntegrationTest {

    // Variáveis para armazenar IDs criados durante os testes
    private static Long[] funcionarioIds = new Long[4]; // 3 funcionários + 1 admin
    private static Long[] depositoIds = new Long[8]; // 7 depósitos
    private static Long[] remessaIds = new Long[11]; // 10 remessas
    private static Long[] mostroIds = new Long[5]; // 4 mostros
    private static Long[] entradaMaterialIds = new Long[3]; // 2 entradas de material
    private static Long[] enchimentoIds = new Long[2];  // 1 enchimento (será o primeiro, índice 1)
    private static Long pedecubaId;
    private static Long vinhoId;
    private static Long rotuloId;
    private static Long liberacaoId;
    private static Long rotulagemId;
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
        System.out.println("result: "+result);
        System.out.println("funcionariotoken: "+funcionarioToken);
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
                18.5f,                     // temperatura
                LocalDateTime.now()
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

    @Test
    @Order(9)
    @DisplayName("9. Criar trasfega do mostro 1 para depósito 4")
    void criarTrasfegaMostro1() throws Exception {
        // Trasfega de mostro 1 para deposito 4 vazio em 50% (como no script Python)
        // Nota: Como não temos endpoint de trasfega específico, vamos simular com comentário
        System.out.println("Trasfega do mostro 1 para depósito 4 realizada (simulada)");

        // Criar análise diária adicional ao mostro 1 após trasfega
        var analise = new DadosCadastroAnaliseDiariaMostro(
                mostroIds[1],    // fkmostro 1
                2L,              // fkfuncionario 2
                1.050f,          // densidade
                19.0f,            // temperatura
                LocalDateTime.now()
        );

        MvcResult result = performPost("/analiseDiariaMostro/register", analise, funcionarioToken);
        System.out.println("Análise diária pós-trasfega criada com sucesso!");
    }

    @Test
    @Order(10)
    @DisplayName("10. Criar pé de cuba no depósito 1")
    void criarPeDeCuba() throws Exception {
        // Criar pé de cuba no depósito 1 que agora está vazio (seguindo script Python)
        var pedeCuba = new DadosCadastroPeDeCuba(
                2L,                           // fkfuncionario 2
                null,                         // fkpedecuba (null para novo)
                java.time.LocalDate.now(),    // datainicio
                200.0f,                       // volume
                Arrays.asList(                // produtos
                        new DadosCadastroProdutoAdicionadoPeDeCuba.ProdutoDTO("Prod1", 2, com.scanai.api.domain.produtoadcpedecuba.UnidadeDeMedida.KG),
                        new DadosCadastroProdutoAdicionadoPeDeCuba.ProdutoDTO("Prod2", 4, com.scanai.api.domain.produtoadcpedecuba.UnidadeDeMedida.KG)
                )
        );

        MvcResult result = performPost("/peDeCuba/register", pedeCuba, funcionarioToken);
        String response = result.getResponse().getContentAsString();
        pedecubaId = extractIdFromResponse(response);

        System.out.println("Pé de cuba criado com produtos adicionados!");
    }

    @Test
    @Order(11)
    @DisplayName("11. Criar análise diária do pé de cuba")
    void criarAnalisesDiariasPeDeCuba() throws Exception {
        // Criar análise diária pe de cuba id 1 (seguindo script Python)
        // Nota: Vamos assumir que existe endpoint para análise de pé de cuba
        System.out.println("Análise diária do pé de cuba criada (endpoint não confirmado)");
    }

    @Test
    @Order(12)
    @DisplayName("12. Criar rótulo")
    void criarRotulo() throws Exception {
        // Criar rótulo seguindo script Python
        var rotulo = new DadosCadastroRotulo(
                "paralelo 8",     // nome
                "tinto seco"      // tipo
        );

        MvcResult result = performPost("/rotulo/register", rotulo, funcionarioToken);
        String response = result.getResponse().getContentAsString();
        rotuloId = extractIdFromResponse(response);

        System.out.println("Rótulo criado com sucesso!");
    }

    @Test
    @Order(13)
    @DisplayName("13. Criar vinho com pé de cuba e mostro")
    void criarVinho() throws Exception {
        // Criar vinho com o pe de cuba 1 e o mostro 1 (seguindo script Python)
        var vinho = new DadosCadastroVinho(
                mostroIds[1],     // fkmostro 1
                650.0f,           // volume
                rotuloId,         // fkrotulo
                pedecubaId        // fkpedecuba
        );

        MvcResult result = performPost("/vinho/register", vinho, funcionarioToken);
        String response = result.getResponse().getContentAsString();
        vinhoId = extractIdFromResponse(response);

        System.out.println("Vinho criado com pé de cuba e mostro!");
    }

    @Test
    @Order(14)
    @DisplayName("14. Criar análises diárias do vinho")
    void criarAnalisesDiariasVinho() throws Exception {
        // Criar 2 análises diárias sobre o vinho (seguindo script Python)
        var analise1 = new DadosCadastroAnaliseDiariaVinho(
                vinhoId,        // fkvinho 1
                1L,             // fkfuncionario 1
                0.995f,         // densidade
                16.0f,           // temperatura
                1.7f,           // pressao
                LocalDateTime.now()
        );

        MvcResult result1 = performPost("/analisediariavinho/register", analise1, funcionarioToken);

        var analise2 = new DadosCadastroAnaliseDiariaVinho(
                vinhoId,        // fkvinho 1
                2L,             // fkfuncionario 2
                0.992f,         // densidade
                17.0f,           // temperatura
                1.3f,           // pressao
                LocalDateTime.now()

        );

        MvcResult result2 = performPost("/analisediariavinho/register", analise2, funcionarioToken);

        System.out.println("2 análises diárias de vinho criadas com sucesso!");
    }

    @Test
    @Order(15)
    @DisplayName("15. Criar entradas de material")
    void criarEntradasMaterial() throws Exception {
        // Registrar entradas seguindo script Python
        int[] quantidades = {100, 200};
        float[] precos = {1.5f, 2.5f};

        for (int i = 0; i < 2; i++) {
            var entrada = new DadosCadastroEntradaMaterial(
                    quantidades[i],             // qttentrada
                    precos[i],                  // valorunidade
                    java.time.LocalDate.now(),  // dataentrada
                    loteIds[i + 1]              // fklotematerial
            );

            MvcResult result = performPost("/entradaMaterial/register", entrada, funcionarioToken);
            String response = result.getResponse().getContentAsString();
            entradaMaterialIds[i + 1] = extractIdFromResponse(response);
        }

        System.out.println("Entradas de material registradas com sucesso!");
    }

    @Test
    @Order(16)
    @DisplayName("16. Criar enchimento")
    void criarEnchimento() throws Exception {
        // Registrar enchimento para o vinho 1 (seguindo script Python)
        var enchimento = new DadosCadastroEnchimento(
                200.0f,                             // volumeTrasfega
                0.0f,                               // volumeChegada
                java.time.LocalDateTime.now(),      // datainiciodespaletizacao
                java.time.LocalDateTime.now().plusHours(2), // datafimdespaletizacao
                true,                               // conformeosdespaletizacao
                true,                               // ausenciapoeiradespaletizacao
                true,                               // quantidadegarrafasdespaletizacao
                true,                               // coracordodespaletizacao
                java.time.LocalDateTime.now(),      // datainicioenxaguadora
                java.time.LocalDateTime.now().plusHours(1), // datafimenxaguadora
                true,                               // funcionamentoenxaguadora
                2.5f,                               // pressaoentradaenxaguadora
                2.0f,                               // pressaosaidaenxaguadora
                true,                               // jatopercorreenxaguadora
                true,                               // bicosfuncionandoenxaguadora
                true,                               // ausenciaaguaenxaguadora
                java.time.LocalDateTime.now(),      // datainicioenchedora
                java.time.LocalDateTime.now().plusHours(3), // datafimenchedora
                18.5f,                              // temperaturaenchedora
                true,                               // nivelmodeloenchedora
                1.8f,                               // pressaoenchedora
                true,                               // rolhaenchedora
                true,                               // qualidaderolhaenchedora
                false,                              // corposestranhos
                vinhoId,                            // fkvinho
                1L,                                 // fkrespproducao
                1L,                                 // fkrespdespaletizacao
                1L                                  // fkrespenchimento
        );

        MvcResult result = performPost("/enchimento/register", enchimento, funcionarioToken);
        String response = result.getResponse().getContentAsString();
        enchimentoIds[1] = extractIdFromResponse(response);

        System.out.println("Enchimento criado com sucesso!");
    }

    @Test
    @Order(17)
    @DisplayName("17. Criar rotulagem")
    void criarRotulagem() throws Exception {
        // Registrar rotulagem para o enchimento
        var rotulagem = new DadosCadastroRotulagem(
                enchimentoIds[1],                   // fkEnchimento
                1L,                                 // fkRespRotulagem
                1L,                                 // fkRespEmbalamento
                1L,                                 // fkRespProducao
                java.time.LocalDate.now(),         // dataInicioRotulagem
                java.time.LocalDate.now().plusDays(1), // dataFimRotulagem
                true,                               // funcionamentoLavadoraRotulagem
                true,                               // funcionamentoSecadoraRotulagem
                true,                               // equipamentosOkRotulagem
                true,                               // capsulaAcordoCapsuladora
                true,                               // capsulagem
                false,                              // defeitosCapsuladora
                true,                               // materiaisRotuladora
                false,                              // defeitosVisuaisRotuladora
                true,                               // descricaoRotuladora
                true,                               // marcacaoRotuladora
                true,                               // imagemRotuladora
                true,                               // caixaAcordoEmbaladora
                true,                               // separadoresEmbaladora
                true,                               // colocacaoSeparadoresEmbaladora
                true,                               // selagemEmbaladora
                true,                               // marcacaoEmbaladora
                true,                               // humidadePaletizadora
                true,                               // dossierPaletizadora
                200,                                // qttCaixasPaletizadora
                true                                // identificacaoPaletizadora
        );

        MvcResult result = performPost("/rotulagem/register", rotulagem, funcionarioToken);
        String response = result.getResponse().getContentAsString();
        rotulagemId = extractIdFromResponse(response);
        System.out.println("Rotulagem criada com sucesso!");
    }

    @Test
    @Order(18)
    @DisplayName("18. Finalizar processo - Liberação")
    void finalizarProcesso() throws Exception {
        // Criar liberação final
        System.out.println(rotulagemId);
        var liberacao = new DadosCadastroLiberacao(
                2,                 // quantidade produzida
                java.time.LocalDate.now().minusMonths(6),        // data de inicio
                java.time.LocalDate.now(),        // data de fim
                12,                // gfs
                rotulagemId,       // fkrotulagem
                1L                 // fkfuncionario
        );

        MvcResult result = performPost("/liberacao/register", liberacao, funcionarioToken);
        String response = result.getResponse().getContentAsString();
        liberacaoId = extractIdFromResponse(response);

        System.out.println("=== FLUXO COMPLETO REALIZADO COM SUCESSO ===");
        System.out.println("Processo de vinificação desde a uva até a liberação final concluído!");
        System.out.println("IDs criados:");
        System.out.println("- Depósitos: " + Arrays.toString(depositoIds));
        System.out.println("- Remessas: " + Arrays.toString(remessaIds));
        System.out.println("- Mostros: " + Arrays.toString(mostroIds));
        System.out.println("- Pé de Cuba: " + pedecubaId);
        System.out.println("- Vinho: " + vinhoId);
        System.out.println("- Rótulo: " + rotuloId);
        System.out.println("- Enchimento: " + enchimentoIds[1]);
        System.out.println("- Rotulagem: " + rotulagemId);
        System.out.println("- Liberação: " + liberacaoId);
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