package br.com.ourogourmet.application.controller;

import br.com.ourogourmet.application.controller.dtos.AlterarTipoUsuarioDTO;
import br.com.ourogourmet.application.controller.dtos.CriarTipoUsuarioDTO;
import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.entities.enums.TipoUsuarioEnum;
import br.com.ourogourmet.domain.exceptions.TipoUsuarioCamposInvalidosException;
import br.com.ourogourmet.domain.usecases.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TipoUsuarioControllerTest {

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

    @InjectMocks
    private TipoUsuarioController controller;

    private CriarTipoUsuarioDTO criarTipoUsuarioDTO;
    private AlterarTipoUsuarioDTO alterarTipoUsuarioDTO;

    @BeforeEach
    void setUp() {
        criarTipoUsuarioDTO = new CriarTipoUsuarioDTO(TipoUsuarioEnum.DONO);
        alterarTipoUsuarioDTO = new AlterarTipoUsuarioDTO(TipoUsuarioEnum.CLIENTE);
    }

    @Test
    void criarTipoUsuario_ComDadosValidos_DeveRetornarOk() {
        when(validator.validateObject(any())).thenReturn(new BeanPropertyBindingResult(criarTipoUsuarioDTO, "criarTipoUsuarioDTO"));
        when(criarTipoUsuario.execute(any())).thenReturn(1L);

        ResponseEntity<Void> response = controller.criarTipoUsuario(criarTipoUsuarioDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(criarTipoUsuario).execute(any());
    }

    @Test
    void alterarTipoUsuario_ComDadosValidos_DeveRetornarNoContent() {
        Long id = 1L;
        when(validator.validateObject(any())).thenReturn(new BeanPropertyBindingResult(alterarTipoUsuarioDTO, "alterarTipoUsuarioDTO"));

        ResponseEntity<Void> response = controller.alterarTipoUsuario(id, alterarTipoUsuarioDTO);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(alterarTipoUsuario).execute(any());
    }

    @Test
    void alterarTipoUsuario_ComDadosInvalidos_DeveLancarExcecao() {
        Long id = 1L;
        Errors errors = new BeanPropertyBindingResult(alterarTipoUsuarioDTO, "alterarTipoUsuarioDTO");
        errors.reject("tipoUsuario", "Tipo é obrigatório");

        when(validator.validateObject(any())).thenReturn(errors);

        assertThrows(TipoUsuarioCamposInvalidosException.class, () -> {
            controller.alterarTipoUsuario(id, alterarTipoUsuarioDTO);
        });
    }

    @Test
    void deletarTipoUsuario_DeveRetornarOk() {
        String id = "1";

        ResponseEntity<Void> response = controller.delete(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(deletarTipoUsuario).delete(eq(id));
    }

    @Test
    void buscarTodosTipoUsuario_DeveRetornarLista() {
        when(getAllTipoUsuario.findAll(anyInt(), anyInt())).thenReturn(Collections.emptyList());

        ResponseEntity<List<TipoUsuario>> response = controller.findAll(1, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(getAllTipoUsuario).findAll(eq(1), eq(10));
    }

    @Test
    void buscarTipoUsuarioPorId_DeveRetornarTipoUsuario() {
        String id = "1";
        TipoUsuario tipo = TipoUsuario.create(TipoUsuarioEnum.CLIENTE);
        when(getByIdTipoUsuario.findById(anyString())).thenReturn(tipo);

        ResponseEntity<TipoUsuario> response = controller.findById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tipo, response.getBody());
        verify(getByIdTipoUsuario).findById(eq(id));
    }
}