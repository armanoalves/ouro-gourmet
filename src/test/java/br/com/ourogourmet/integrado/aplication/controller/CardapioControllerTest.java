package br.com.ourogourmet.integrado.aplication.controller;

import br.com.ourogourmet.application.controller.CardapioController;
import br.com.ourogourmet.application.response.GlobalExceptionHandle;
import br.com.ourogourmet.domain.entities.Cardapio;
import br.com.ourogourmet.domain.usecases.*;
import br.com.ourogourmet.helper.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CardapioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CriarCardapioUseCase criarCardapio;
    @Mock
    private AlterarCardapioUseCase alterarCardapio;
    @Mock
    private DeletarCardapioUseCase deletarCardapio;
    @Mock
    private GetAllCardapioUseCase getAllCardapios;
    @Mock
    private GetByIdCardapioUseCase getByIdCardapio;

    private Validator validator;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        validator = Mockito.mock(Validator.class);
        mock = MockitoAnnotations.openMocks(this);
        CardapioController cardapioController = new CardapioController(criarCardapio,
                alterarCardapio,
                deletarCardapio,
                getAllCardapios,
                getByIdCardapio,
                validator);
        mockMvc = MockMvcBuilders.standaloneSetup(cardapioController)
                .setControllerAdvice(new GlobalExceptionHandle())
                .addFilter((request, response, filterChain) -> {
                    response.setCharacterEncoding("UTF-8");
                    filterChain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveCriarCardapioComSucesso() throws Exception {
        Cardapio cardapio = Utils.buildCardapio();
        Mockito.when(validator.validateObject(Mockito.any()))
                .thenReturn(new BeanPropertyBindingResult(new Object(), "objectName"));

        mockMvc.perform(post("/cardapio")
                        .contentType("application/json")
                        .content(asJsonString(cardapio)))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(criarCardapio, times(1)).criar(Mockito.any());
    }

    @Test
    void deveBuscarTodosCardapiosComSucesso() throws Exception {

        mockMvc.perform(get("/cardapio?page=1&size=10")
                .contentType("application/json"))
                .andExpect(status().isOk());
        verify(getAllCardapios, times(1)).findAll(Mockito.anyInt(), Mockito.anyInt());
    }

    @Test
    void deveBuscarCardapiosPorIdComSucesso() throws Exception {
        var id = 99L;
        Mockito.when(validator.validateObject(Mockito.any()))
                .thenReturn(new BeanPropertyBindingResult(new Object(), "objectName"));
        mockMvc.perform(get("/cardapio/{id}", id)
                        .contentType("application/json"))
                .andExpect(status().isOk());
        verify(getByIdCardapio, times(1)).findById(Mockito.any());
    }

    @Test
    void deveAtualizarCardapioComSucesso() throws Exception {
        var id = 98L;

        Mockito.when(validator.validateObject(Mockito.any()))
                .thenReturn(new BeanPropertyBindingResult(new Object(), "objectName"));
        mockMvc.perform(get("/cardapio/{id}", id)
                        .contentType("application/json"))
                .andExpect(status().isOk());
        verify(getByIdCardapio, times(1)).findById(Mockito.any());
    }

    @Test
    void deveDeletarCardapioComSucesso() throws Exception {
        var id = 98L;

        Mockito.when(validator.validateObject(Mockito.any()))
                .thenReturn(new BeanPropertyBindingResult(new Object(), "objectName"));
        mockMvc.perform(get("/cardapio/{id}", id)
                        .contentType("application/json"))
                .andExpect(status().isOk());
        verify(getByIdCardapio, times(1)).findById(Mockito.any());
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}