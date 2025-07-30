package br.com.ourogourmet.integrado.controller;

import br.com.ourogourmet.application.controller.TipoUsuarioController;
import br.com.ourogourmet.application.response.GlobalExceptionHandle;
import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.entities.enums.TipoUsuarioEnum;
import br.com.ourogourmet.domain.usecases.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TipoUsuarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CriarTipoUsuarioUseCase criarTipoUsuario;

    @Mock
    private AlterarTipoUsuarioUseCase alterarTipoUsuario;

    @Mock
    private DeletarTipoUsuarioUseCase deletarTipoUsuario;

    @Mock
    private GetAllTipoUsuarioUseCase getAllTipoUsuario;

    @Mock
    private GetByIdTipoUsuarioUseCase getByIdTipoUsuario;

    @Mock
    private Validator validator;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        TipoUsuarioController controller = new TipoUsuarioController(
                criarTipoUsuario, alterarTipoUsuario, deletarTipoUsuario,
                getAllTipoUsuario, getByIdTipoUsuario, validator
        );
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandle())
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void deveCriarTipoUsuarioComSucesso() throws Exception {
        var command = new CriarTipoUsuarioUseCase.CriarTipoUsuarioCommand(TipoUsuarioEnum.CLIENTE);
        when(criarTipoUsuario.execute(any())).thenReturn(1L);

        mockMvc.perform(post("/tipo_usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new CriarTipoUsuarioDTO(TipoUsuarioEnum.CLIENTE))))
                .andExpect(status().isOk());

        verify(criarTipoUsuario, times(1)).execute(any());
    }

    @Test
    void deveBuscarTodosTipoUsuarioComSucesso() throws Exception {
        when(getAllTipoUsuario.findAll(anyInt(), anyInt())).thenReturn(List.of());

        mockMvc.perform(get("/tipo_usuario?page=1&size=10"))
                .andExpect(status().isOk());

        verify(getAllTipoUsuario).findAll(1, 10);
    }

    @Test
    void deveBuscarTipoUsuarioPorIdComSucesso() throws Exception {
        TipoUsuario tipoUsuario = TipoUsuario.create(TipoUsuarioEnum.DONO);
        tipoUsuario.setId(1L);
        when(getByIdTipoUsuario.findById("1")).thenReturn(tipoUsuario);

        mockMvc.perform(get("/tipo_usuario/1"))
                .andExpect(status().isOk());

        verify(getByIdTipoUsuario).findById("1");
    }

    @Test
    void deveAtualizarTipoUsuarioComSucesso() throws Exception {
        when(validator.validateObject(any()))
                .thenReturn(new BeanPropertyBindingResult(new Object(), "objectName"));

        mockMvc.perform(put("/tipo_usuario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new AlterarTipoUsuarioDTO(TipoUsuarioEnum.CLIENTE))))
                .andExpect(status().isNoContent());

        verify(alterarTipoUsuario).execute(any());
    }

    @Test
    void deveDeletarTipoUsuarioComSucesso() throws Exception {
        mockMvc.perform(delete("/tipo_usuario/1"))
                .andExpect(status().isOk());

        verify(deletarTipoUsuario).delete("1");
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // DTOs para o teste
    record CriarTipoUsuarioDTO(TipoUsuarioEnum tipoUsuarioEnum) {}
    record AlterarTipoUsuarioDTO(TipoUsuarioEnum tipoUsuario) {}
}