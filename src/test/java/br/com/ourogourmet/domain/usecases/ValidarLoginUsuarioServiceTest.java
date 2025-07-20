package br.com.ourogourmet.domain.usecases;
import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.domain.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.domain.exceptions.UsuarioSenhaInvalidaException;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.ValidarLoginUsuarioUseCase.ValidarLoginUsuarioCommand;
import br.com.ourogourmet.domain.usecases.implementations.ValidarLoginUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidarLoginUsuarioServiceTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private ValidarLoginUsuarioService validarLoginUsuarioService;

    @Test
    void validar_ComCredenciaisCorretas_DeveValidarComSucesso() {
        // Arrange
        String login = "usuario.valido";
        String senha = "senha123";
        ValidarLoginUsuarioCommand command = new ValidarLoginUsuarioCommand(login, senha);

        Usuario usuarioMock = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                login, // login diferente do comando
                true,
                senha,
                LocalDate.now(),
                "Rua A, 345");

        when(usuarioGateway.findByLogin(login)).thenReturn(usuarioMock);

        // Act & Assert
        assertDoesNotThrow(() -> {
            validarLoginUsuarioService.validar(command);
        });

        verify(usuarioGateway).findByLogin(login);
    }

    @Test
    void validar_ComSenhaIncorreta_DeveLancarExcecao() {
        // Arrange
        String login = "usuario.valido";
        String senhaCorreta = "senha123";
        String senhaIncorreta = "senhaErrada";
        ValidarLoginUsuarioCommand command = new ValidarLoginUsuarioCommand(login, senhaIncorreta);

        Usuario usuarioMock = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                login, // login diferente do comando
                true,
                senhaCorreta,
                LocalDate.now(),
                "Rua A, 345");

        when(usuarioGateway.findByLogin(login)).thenReturn(usuarioMock);

        // Act & Assert
        UsuarioSenhaInvalidaException exception = assertThrows(UsuarioSenhaInvalidaException.class, () -> {
            validarLoginUsuarioService.validar(command);
        });

        assertNotNull(exception);
        verify(usuarioGateway).findByLogin(login);
    }

    @Test
    void validar_ComUsuarioNaoEncontrado_DeveLancarExcecao() {
        // Arrange
        String login = "usuario.inexistente";
        ValidarLoginUsuarioCommand command = new ValidarLoginUsuarioCommand(login, "qualquerSenha");

        when(usuarioGateway.findByLogin(login)).thenReturn(null);

        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            validarLoginUsuarioService.validar(command);
        });
    }

    @Test
    void validar_ComLoginNulo_DeveLancarExcecao() {
        // Arrange
        ValidarLoginUsuarioCommand command = new ValidarLoginUsuarioCommand(null, "senha123");

        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            validarLoginUsuarioService.validar(command);
        });
    }

    @Test
    void validar_ComSenhaNula_DeveLancarExcecao() {
        // Arrange
        String login = "usuario.valido";
        ValidarLoginUsuarioCommand command = new ValidarLoginUsuarioCommand(login, null);

        Usuario usuarioMock = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                login, // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345");

        when(usuarioGateway.findByLogin(login)).thenReturn(usuarioMock);

        // Act & Assert
        assertThrows(UsuarioSenhaInvalidaException.class, () -> {
            validarLoginUsuarioService.validar(command);
        });
    }

    @Test
    void validar_DeveChamarFindByLoginApenasUmaVez() {
        // Arrange
        String login = "usuario.valido";
        String senha = "senha123";
        ValidarLoginUsuarioCommand command = new ValidarLoginUsuarioCommand(login, senha);

        Usuario usuarioMock = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                login, // login diferente do comando
                true,
                senha,
                LocalDate.now(),
                "Rua A, 345");

        when(usuarioGateway.findByLogin(login)).thenReturn(usuarioMock);

        // Act
        validarLoginUsuarioService.validar(command);

        // Assert
        verify(usuarioGateway, times(1)).findByLogin(login);
        verifyNoMoreInteractions(usuarioGateway);
    }

    @Test
    void validar_ComCaseSensitive_DeveValidarCorretamente() {
        // Arrange
        String login = "Usuario.Valido";
        String senha = "Senha123";
        ValidarLoginUsuarioCommand command = new ValidarLoginUsuarioCommand(login, senha);

        Usuario usuarioMock = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                login, // login diferente do comando
                true,
                senha,
                LocalDate.now(),
                "Rua A, 345");

        when(usuarioGateway.findByLogin(login)).thenReturn(usuarioMock);

        // Act & Assert
        assertDoesNotThrow(() -> {
            validarLoginUsuarioService.validar(command);
        });

        // Teste com case diferente
        ValidarLoginUsuarioCommand commandCaseDiferente = new ValidarLoginUsuarioCommand(login.toLowerCase(), senha.toUpperCase());
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            validarLoginUsuarioService.validar(commandCaseDiferente);
        });
    }
}