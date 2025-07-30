package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.domain.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.TrocarSenhaUsuarioUseCase.TrocarSenhaUsuarioCommand;
import br.com.ourogourmet.domain.usecases.implementations.TrocarSenhaUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrocarSenhaUsuarioServiceTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private TrocarSenhaUsuarioService trocarSenhaUsuarioService;

    @Test
    void trocarSenha_ComDadosValidos_DeveAtualizarSenha() {
        // Arrange
        String id = "user-123";
        String novaSenha = "novaSenhaSegura123";
        TrocarSenhaUsuarioCommand command = new TrocarSenhaUsuarioCommand(novaSenha);

        Usuario usuarioMock = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345",
                1L);
        when(usuarioGateway.findById(id)).thenReturn(usuarioMock);
        when(usuarioGateway.atualizarSenha(any(Usuario.class), eq(id))).thenReturn(1);

        // Act
        trocarSenhaUsuarioService.trocarSenha(command, id);

        // Assert
        verify(usuarioGateway).findById(id);
        verify(usuarioGateway).atualizarSenha(argThat(u ->
                u.getSenha().equals(novaSenha)
        ), eq(id));
    }

    @Test
    void trocarSenha_ComUsuarioNaoEncontrado_DeveLancarExcecao() {
        // Arrange
        String id = "user-404";
        TrocarSenhaUsuarioCommand command = new TrocarSenhaUsuarioCommand("qualquerSenha");

        when(usuarioGateway.findById(id)).thenReturn(null);

        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            trocarSenhaUsuarioService.trocarSenha(command, id);
        });

        verify(usuarioGateway, never()).atualizarSenha(any(), any());
    }

    @Test
    void trocarSenha_ComFalhaNaAtualizacao_DeveLancarExcecao() {
        // Arrange
        String id = "user-123";
        TrocarSenhaUsuarioCommand command = new TrocarSenhaUsuarioCommand("novaSenha");

        Usuario usuarioMock = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345",
                1L);
        when(usuarioGateway.findById(id)).thenReturn(usuarioMock);
        when(usuarioGateway.atualizarSenha(any(Usuario.class), eq(id))).thenReturn(0);

        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            trocarSenhaUsuarioService.trocarSenha(command, id);
        });
    }

    @Test
    void trocarSenha_DeveChamarMetodosNaOrdemCorreta() {
        // Arrange
        String id = "user-123";
        TrocarSenhaUsuarioCommand command = new TrocarSenhaUsuarioCommand("senha123");

        Usuario usuarioMock = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345",
                1L);
        when(usuarioGateway.findById(id)).thenReturn(usuarioMock);
        when(usuarioGateway.atualizarSenha(any(Usuario.class), eq(id))).thenReturn(1);

        // Act
        trocarSenhaUsuarioService.trocarSenha(command, id);

        // Assert
        InOrder inOrder = inOrder(usuarioGateway);
        inOrder.verify(usuarioGateway).findById(id);
        inOrder.verify(usuarioGateway).atualizarSenha(any(Usuario.class), eq(id));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void trocarSenha_ComSenhaNula_DeveLancarExcecao() {
        // Arrange
        String id = "user-123";
        TrocarSenhaUsuarioCommand command = new TrocarSenhaUsuarioCommand(null);

        Usuario usuarioMock =Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345",
                1L);
        when(usuarioGateway.findById(id)).thenReturn(usuarioMock);

        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            trocarSenhaUsuarioService.trocarSenha(command, id);
        });
    }

    @Test
    void trocarSenha_ComIdNulo_DeveLancarExcecao() {
        // Arrange
        TrocarSenhaUsuarioCommand command = new TrocarSenhaUsuarioCommand("senha123");

        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            trocarSenhaUsuarioService.trocarSenha(command, null);
        });

        verify(usuarioGateway, never()).atualizarSenha(any(), any());
    }

    @Test
    void trocarSenha_DeveAtualizarSenhaCorretamente() {
        // Arrange
        String id = "user-123";
        String novaSenha = "novaSenha@123";
        TrocarSenhaUsuarioCommand command = new TrocarSenhaUsuarioCommand(novaSenha);

        Usuario usuarioMock =Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senhaAntiga",
                LocalDate.now(),
                "Rua A, 345",
                1L);

        when(usuarioGateway.findById(id)).thenReturn(usuarioMock);
        when(usuarioGateway.atualizarSenha(any(Usuario.class), eq(id))).thenReturn(1);

        // Act
        trocarSenhaUsuarioService.trocarSenha(command, id);

        // Assert
        verify(usuarioGateway).atualizarSenha(argThat(u ->
                u.getSenha().equals(novaSenha)
        ), eq(id));
    }
}