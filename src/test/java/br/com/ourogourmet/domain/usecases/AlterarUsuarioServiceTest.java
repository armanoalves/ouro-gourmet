package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.domain.exceptions.UsuarioDuplicadoException;
import br.com.ourogourmet.domain.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.domain.exceptions.UsuarioValidacaoException;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.AlterarUsuarioUseCase.AlterarUsuarioCommand;
import br.com.ourogourmet.domain.usecases.implementations.AlterarUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlterarUsuarioServiceTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private AlterarUsuarioService alterarUsuarioService;

    @Test
    void update_ComDadosValidos_DeveAtualizarUsuario() {
        // Arrange
        String id = "user-123";
        AlterarUsuarioCommand command = new AlterarUsuarioCommand(
                "João Silva Atualizado",
                "Rua Nova, 456",
                "joao.novo@email.com",
                "joaosilva", // login mantido igual
                true,
                1L);

        Usuario usuarioExistente = Usuario.create(
                "João Silva",
                "joao@email.com",
                "joaosilva", // login igual ao comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua Antiga, 123",
                1L);

        when(usuarioGateway.findById(id)).thenReturn(usuarioExistente);
        when(usuarioGateway.existsByEmail(command.email())).thenReturn(false);
        when(usuarioGateway.update(any(Usuario.class), eq(id))).thenReturn(1);

        // Act
        alterarUsuarioService.update(command, id);

        // Assert
        verify(usuarioGateway).update(argThat(usuario ->
                usuario.getNome().equals("João Silva Atualizado") &&
                        usuario.getEndereco().equals("Rua Nova, 456") &&
                        usuario.getEmail().equals("joao.novo@email.com") &&
                        usuario.getLogin().equals("joaosilva") &&
                        usuario.getTipoUsuarioId().equals(1L) &&
                        usuario.getAtivo()

        ), eq(id));
    }

    @Test
    void update_ComUsuarioNaoExistente_DeveLancarExcecao() {
        // Arrange
        String id = "user-404";
        AlterarUsuarioCommand command = new AlterarUsuarioCommand(
                "Nome Qualquer",
                "Endereço Qualquer",
                "email@qualquer.com",
                "loginqualquer",
                true,
                1L);

        when(usuarioGateway.findById(id)).thenReturn(null);

        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            alterarUsuarioService.update(command, id);
        });
    }

    @Test
    void update_ComEmailDuplicado_DeveLancarExcecao() {
        // Arrange
        String id = "user-123";
        AlterarUsuarioCommand command = new AlterarUsuarioCommand(
                "João Silva",
                "Rua A, 123",
                "email.existente@teste.com", // email que já existe
                "joaosilva",
                true,
                1L);

        Usuario usuarioExistente = Usuario.create(
                "João Silva",
                "joao@email.com",
                "joaosilva",
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 123",
                1L);

        when(usuarioGateway.findById(id)).thenReturn(usuarioExistente);
        when(usuarioGateway.existsByEmail(command.email())).thenReturn(true);

        // Act & Assert
        UsuarioDuplicadoException exception = assertThrows(UsuarioDuplicadoException.class, () -> {
            alterarUsuarioService.update(command, id);
        });

        assertEquals("A informação do e-mail já existe no banco", exception.getMessage());
    }

    @Test
    void update_ComLoginDiferente_DeveLancarExcecao() {
        // Arrange
        String id = "user-123";
        AlterarUsuarioCommand command = new AlterarUsuarioCommand(
                "João Silva",
                "Rua A, 123",
                "joao@email.com",
                "novologin", // login diferente do existente
                true,
                1L);

        Usuario usuarioExistente = Usuario.create(
                "João Silva",
                "joao@email.com",
                "loginantigo", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 123",
                1L);

        when(usuarioGateway.findById(id)).thenReturn(usuarioExistente);

        // Act & Assert
        UsuarioDuplicadoException exception = assertThrows(UsuarioDuplicadoException.class, () -> {
            alterarUsuarioService.update(command, id);
        });

        assertEquals("A informação do login já existe no banco", exception.getMessage());
    }

    @Test
    void update_ComUsuarioInativo_DeveLancarExcecao() {
        // Arrange
        String id = "user-123";
        AlterarUsuarioCommand command = new AlterarUsuarioCommand(
                "João Silva",
                "Rua A, 123",
                "joao@email.com",
                "joaosilva",
                false,
                1L); // ativo = false

        Usuario usuarioExistente = Usuario.create(
                "João Silva",
                "joao@email.com",
                "joaosilva",
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 123",
                1L);

        when(usuarioGateway.findById(id)).thenReturn(usuarioExistente);

        // Act & Assert
        UsuarioValidacaoException exception = assertThrows(UsuarioValidacaoException.class, () -> {
            alterarUsuarioService.update(command, id);
        });

        assertEquals("O campo 'ativo' não pode ser falso.", exception.getMessage());
    }

    @Test
    void update_ComFalhaNaAtualizacao_DeveLancarExcecao() {
        // Arrange
        String id = "user-123";
        AlterarUsuarioCommand command = new AlterarUsuarioCommand(
                "João Silva",
                "Rua A, 123",
                "joao@email.com",
                "joaosilva",
                true,
                1L);

        Usuario usuarioExistente = Usuario.create(
                "João Silva",
                "joao@email.com",
                "joaosilva",
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 123",
                1L);

        when(usuarioGateway.findById(id)).thenReturn(usuarioExistente);
        when(usuarioGateway.existsByEmail(command.email())).thenReturn(false);
        when(usuarioGateway.update(any(Usuario.class), eq(id))).thenReturn(0);

        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            alterarUsuarioService.update(command, id);
        });
    }

    @Test
    void update_DeveManterMesmoLogin() {
        // Arrange
        String id = "user-123";
        AlterarUsuarioCommand command = new AlterarUsuarioCommand(
                "João Silva Atualizado",
                "Rua Nova, 456",
                "joao.novo@email.com",
                "joaosilva", // mesmo login
                true,
                1L);

        Usuario usuarioExistente = Usuario.create(
                "João Silva",
                "joao@email.com",
                "joaosilva", // mesmo login
                true,
                "senha123",
                LocalDate.now(),
                "Rua Antiga, 123",
                1L);

        when(usuarioGateway.findById(id)).thenReturn(usuarioExistente);
        when(usuarioGateway.existsByEmail(command.email())).thenReturn(false);
        when(usuarioGateway.update(any(Usuario.class), eq(id))).thenReturn(1);

        // Act
        alterarUsuarioService.update(command, id);

        // Assert
        verify(usuarioGateway).update(argThat(usuario ->
                usuario.getLogin().equals("joaosilva")
        ), eq(id));
    }
}