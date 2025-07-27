package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.exceptions.TipoUsuarioNaoDeletadoException;
import br.com.ourogourmet.domain.exceptions.TipoUsuarioNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.TipoUsuarioGateway;
import br.com.ourogourmet.domain.usecases.implementations.DeletarTipoUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarTipoUsuarioServiceTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private DeletarTipoUsuarioService deletarTipoUsuarioService;

    @Test
    void delete_ComSucesso_DeveChamarDeleteNoGateway() {
        // Arrange
        String id = "123";
        when(tipoUsuarioGateway.delete(id)).thenReturn(1);

        // Act
        deletarTipoUsuarioService.delete(id);

        // Assert
        verify(tipoUsuarioGateway).delete(id);
    }

    @Test
    void delete_QuandoTipoUsuarioNaoEncontrado_DeveLancarExcecao() {
        // Arrange
        String id = "999";
        when(tipoUsuarioGateway.delete(id)).thenReturn(0);

        // Act & Assert
        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> {
            deletarTipoUsuarioService.delete(id);
        });

        verify(tipoUsuarioGateway).delete(id);
    }

    @Test
    void delete_QuandoTipoUsuarioNaoDeletado_DeveLancarExcecao() {
        // Arrange
        String id = "555";
        when(tipoUsuarioGateway.delete(id)).thenReturn(-1);

        // Act & Assert
        assertThrows(TipoUsuarioNaoDeletadoException.class, () -> {
            deletarTipoUsuarioService.delete(id);
        });

        verify(tipoUsuarioGateway).delete(id);
    }
}