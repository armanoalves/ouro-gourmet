package br.com.ourogourmet.application.controller;

import br.com.ourogourmet.application.controller.dtos.AlterarUsuarioDTO;
import br.com.ourogourmet.application.controller.dtos.CriarUsuarioDTO;
import br.com.ourogourmet.application.controller.dtos.TrocarSenhaUsuarioDTO;
import br.com.ourogourmet.application.controller.dtos.ValidarLoginUsuarioDTO;
import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.domain.exceptions.UsuarioCamposInvalidosException;
import br.com.ourogourmet.domain.usecases.*;
import br.com.ourogourmet.domain.usecases.AlterarUsuarioUseCase.AlterarUsuarioCommand;
import br.com.ourogourmet.domain.usecases.CriarUsuarioUseCase.CriarUsuarioCommand;
import br.com.ourogourmet.domain.usecases.TrocarSenhaUsuarioUseCase.TrocarSenhaUsuarioCommand;
import br.com.ourogourmet.domain.usecases.ValidarLoginUsuarioUseCase.ValidarLoginUsuarioCommand;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private CriarUsuarioUseCase criarUsuarioUseCase;

    @Mock
    private AlterarUsuarioUseCase alterarUsuarioUseCase;

    @Mock
    private DeletarUsuarioUseCase deletarUsuarioUseCase;

    @Mock
    private GetAllUsuarioUseCase getAllUsuarioUseCase;

    @Mock
    private GetByIdUsuarioUseCase getByIdUsuarioUseCase;

    @Mock
    private TrocarSenhaUsuarioUseCase trocarSenhaUsuarioUseCase;

    @Mock
    private ValidarLoginUsuarioUseCase validarLoginUsuarioUseCase;

    @Mock
    private Validator validator;

    @InjectMocks
    private UsuarioController usuarioController;

    private CriarUsuarioDTO criarUsuarioDTO;
    private AlterarUsuarioDTO alterarUsuarioDTO;
    private TrocarSenhaUsuarioDTO trocarSenhaUsuarioDTO;
    private ValidarLoginUsuarioDTO validarLoginUsuarioDTO;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        criarUsuarioDTO = new CriarUsuarioDTO(
                "João Silva",
                "Rua A, 123",
                "senha123",
                "joao@email.com",
                "joaosilva",
                true,
                1L);

        alterarUsuarioDTO = new AlterarUsuarioDTO(
                "João Silva Atualizado",
                "Rua B, 456",
                "joao.novo@email.com",
                "joaosilva",
                true,
                1L);

        trocarSenhaUsuarioDTO = new TrocarSenhaUsuarioDTO("novaSenha123");
        validarLoginUsuarioDTO = new ValidarLoginUsuarioDTO("joaosilva", "senha123");

        usuario = Usuario.create("João Silva","joao@email.com","joao",true,"senha", LocalDate.now(),"Rua das Flores", 1L);

    }

    // Testes para listagem de usuários
    @Test
    void findAllUsuarios_DeveRetornarListaDeUsuarios() {
        // Arrange
        int page = 1;
        int size = 10;
        when(getAllUsuarioUseCase.findAll(page, size)).thenReturn(Collections.singletonList(usuario));

        // Act
        ResponseEntity<List<Usuario>> response = usuarioController.findAllUsuarios(page, size);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(getAllUsuarioUseCase).findAll(page, size);
    }

    @Test
    void findUsuarioById_DeveRetornarUsuarioQuandoExistir() {
        // Arrange
        String id = "1";
        when(getByIdUsuarioUseCase.findById(id)).thenReturn(usuario);

        // Act
        ResponseEntity<Usuario> response = usuarioController.findUsuarioById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(getByIdUsuarioUseCase).findById(id);
    }

    // Testes para criação de usuário
    @Test
    void criarUsuario_ComDadosValidos_DeveRetornarOk() {
        // Arrange
        when(validator.validateObject(any())).thenReturn(new BeanPropertyBindingResult(criarUsuarioDTO, "criarUsuarioDTO"));

        // Act
        ResponseEntity<Void> response = usuarioController.criarUsuario(criarUsuarioDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(criarUsuarioUseCase).save(any(CriarUsuarioCommand.class));
    }

    @Test
    void criarUsuario_ComDadosInvalidos_DeveLancarExcecao() {
        // Arrange
        Errors errors = new BeanPropertyBindingResult(criarUsuarioDTO, "criarUsuarioDTO");
        errors.reject("nome", "Nome é obrigatório");
        when(validator.validateObject(any())).thenReturn(errors);

        // Act & Assert
        assertThrows(UsuarioCamposInvalidosException.class, () -> {
            usuarioController.criarUsuario(criarUsuarioDTO);
        });
    }

    // Testes para validação de login
    @Test
    void validar_ComCredenciaisValidas_DeveRetornarOk() {
        // Arrange
        when(validator.validateObject(any())).thenReturn(new BeanPropertyBindingResult(validarLoginUsuarioDTO, "validarLoginUsuarioDTO"));

        // Act
        ResponseEntity<String> response = usuarioController.validar(validarLoginUsuarioDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário validado!", response.getBody());
        verify(validarLoginUsuarioUseCase).validar(any(ValidarLoginUsuarioCommand.class));
    }

    @Test
    void validar_ComDadosInvalidos_DeveLancarExcecao() {
        // Arrange
        Errors errors = new BeanPropertyBindingResult(validarLoginUsuarioDTO, "validarLoginUsuarioDTO");
        errors.reject("login", "Login é obrigatório");
        when(validator.validateObject(any())).thenReturn(errors);

        // Act & Assert
        assertThrows(UsuarioCamposInvalidosException.class, () -> {
            usuarioController.validar(validarLoginUsuarioDTO);
        });
    }

    // Testes para atualização de usuário
    @Test
    void updateUsuario_ComDadosValidos_DeveRetornarNoContent() {
        // Arrange
        String id = "1";
        when(validator.validateObject(any())).thenReturn(new BeanPropertyBindingResult(alterarUsuarioDTO, "alterarUsuarioDTO"));

        // Act
        ResponseEntity<Void> response = usuarioController.updateUsuario(id, alterarUsuarioDTO);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(alterarUsuarioUseCase).update(any(AlterarUsuarioCommand.class), eq(id));
    }

    @Test
    void updateUsuario_ComDadosInvalidos_DeveLancarExcecao() {
        // Arrange
        String id = "1";
        Errors errors = new BeanPropertyBindingResult(alterarUsuarioDTO, "alterarUsuarioDTO");
        errors.reject("email", "Email inválido");
        when(validator.validateObject(any())).thenReturn(errors);

        // Act & Assert
        assertThrows(UsuarioCamposInvalidosException.class, () -> {
            usuarioController.updateUsuario(id, alterarUsuarioDTO);
        });
    }

    // Testes para alteração de senha
    @Test
    void alterarSenha_ComDadosValidos_DeveRetornarNoContent() {
        // Arrange
        String id = "1";
        when(validator.validateObject(any())).thenReturn(new BeanPropertyBindingResult(trocarSenhaUsuarioDTO, "trocarSenhaUsuarioDTO"));

        // Act
        ResponseEntity<Void> response = usuarioController.alterarSenha(id, trocarSenhaUsuarioDTO);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(trocarSenhaUsuarioUseCase).trocarSenha(any(TrocarSenhaUsuarioCommand.class), eq(id));
    }

    @Test
    void alterarSenha_ComDadosInvalidos_DeveLancarExcecao() {
        // Arrange
        String id = "1";
        Errors errors = new BeanPropertyBindingResult(trocarSenhaUsuarioDTO, "trocarSenhaUsuarioDTO");
        errors.reject("senha", "Senha é obrigatória");
        when(validator.validateObject(any())).thenReturn(errors);

        // Act & Assert
        assertThrows(UsuarioCamposInvalidosException.class, () -> {
            usuarioController.alterarSenha(id, trocarSenhaUsuarioDTO);
        });
    }

    // Testes para exclusão de usuário
    @Test
    void deleteUsuario_DeveRetornarOk() {
        // Arrange
        String id = "1";

        // Act
        ResponseEntity<Void> response = usuarioController.deleteUsuario(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(deletarUsuarioUseCase).delete(id);
    }
}