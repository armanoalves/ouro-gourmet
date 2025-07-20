package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.exceptions.UsuarioNaoDeletadoException;
import br.com.ourogourmet.domain.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.implementations.DeletarUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarUsuarioServiceTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private DeletarUsuarioService deletarUsuarioService;

    @Test
    void delete_ComUsuarioExistente_DeveDeletarComSucesso() {
        // Arrange
        String id = "user-123";
        when(usuarioGateway.delete(id)).thenReturn(1);

        // Act
        deletarUsuarioService.delete(id);

        // Assert
        verify(usuarioGateway).delete(id);
    }

    @Test
    void delete_ComUsuarioNaoEncontrado_DeveLancarExcecao() {
        // Arrange
        String id = "user-404";
        when(usuarioGateway.delete(id)).thenReturn(0);

        // Act & Assert
        UsuarioNaoEncontradoException exception = assertThrows(UsuarioNaoEncontradoException.class, () -> {
            deletarUsuarioService.delete(id);
        });

        assertNotNull(exception);
        verify(usuarioGateway).delete(id);
    }

    @Test
    void delete_ComFalhaNaDelecao_DeveLancarExcecao() {
        // Arrange
        String id = "user-500";
        when(usuarioGateway.delete(id)).thenReturn(-1);

        // Act & Assert
        UsuarioNaoDeletadoException exception = assertThrows(UsuarioNaoDeletadoException.class, () -> {
            deletarUsuarioService.delete(id);
        });

        assertEquals("Não foi possível deletar o usuário. ID: " + id, exception.getMessage());
        verify(usuarioGateway).delete(id);
    }

    @Test
    void delete_ComDiferentesTiposDeIds_DeveComportarCorretamente() {
        // Teste com ID numérico
        String idNumerico = "123";
        when(usuarioGateway.delete(idNumerico)).thenReturn(1);
        assertDoesNotThrow(() -> deletarUsuarioService.delete(idNumerico));

        // Teste com UUID
        String uuid = "a1b2c3d4-e5f6-7890";
        when(usuarioGateway.delete(uuid)).thenReturn(1);
        assertDoesNotThrow(() -> deletarUsuarioService.delete(uuid));

        // Teste com string longa
        String stringLonga = "id-muito-longo-1234567890";
        when(usuarioGateway.delete(stringLonga)).thenReturn(1);
        assertDoesNotThrow(() -> deletarUsuarioService.delete(stringLonga));

        verify(usuarioGateway, times(3)).delete(anyString());
    }

    @Test
    void delete_DeveChamarMetodoDeleteApenasUmaVez() {
        // Arrange
        String id = "user-123";
        when(usuarioGateway.delete(id)).thenReturn(1);

        // Act
        deletarUsuarioService.delete(id);

        // Assert
        verify(usuarioGateway, times(1)).delete(id);
        verifyNoMoreInteractions(usuarioGateway);
    }

    @Test
    void delete_ComValoresLimite_DeveLidarCorretamente() {
        // Teste com string vazia
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            when(usuarioGateway.delete("")).thenReturn(0);
            deletarUsuarioService.delete("");
        });

        // Teste com string nula
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            deletarUsuarioService.delete(null);
        });
    }
}