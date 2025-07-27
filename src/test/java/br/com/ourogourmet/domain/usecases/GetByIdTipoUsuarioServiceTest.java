package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.entities.enums.TipoUsuarioEnum;
import br.com.ourogourmet.domain.gateway.TipoUsuarioGateway;
import br.com.ourogourmet.domain.usecases.implementations.GetByIdTipoUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetByIdTipoUsuarioServiceTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private GetByIdTipoUsuarioService getByIdTipoUsuarioService;

    @Test
    void findById_ComIdValido_DeveRetornarTipoUsuario() {
        // Arrange
        String id = "1";
        TipoUsuario tipoUsuario = TipoUsuario.create(TipoUsuarioEnum.DONO);
        tipoUsuario.setId(Long.parseLong(id));

        when(tipoUsuarioGateway.findById(id)).thenReturn(tipoUsuario);

        // Act
        TipoUsuario resultado = getByIdTipoUsuarioService.findById(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(TipoUsuarioEnum.DONO, resultado.getTipo());
        assertEquals(1L, resultado.getId());
        verify(tipoUsuarioGateway).findById(id);
    }

    @Test
    void findById_ComIdNulo_DeveLancarExcecao() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            getByIdTipoUsuarioService.findById(null);
        });
    }

    @Test
    void findById_DeveChamarGatewaySomenteUmaVez() {
        // Arrange
        String id = "10";
        TipoUsuario tipoUsuario = TipoUsuario.create(TipoUsuarioEnum.CLIENTE);
        when(tipoUsuarioGateway.findById(id)).thenReturn(tipoUsuario);

        // Act
        getByIdTipoUsuarioService.findById(id);

        // Assert
        verify(tipoUsuarioGateway, times(1)).findById(id);
        verifyNoMoreInteractions(tipoUsuarioGateway);
    }

    @Test
    void findById_DeveRetornarTipoCorreto() {
        // Arrange
        String id = "2";
        TipoUsuario tipoUsuario = TipoUsuario.create(TipoUsuarioEnum.CLIENTE);
        tipoUsuario.setId(2L);

        when(tipoUsuarioGateway.findById(id)).thenReturn(tipoUsuario);

        // Act
        TipoUsuario resultado = getByIdTipoUsuarioService.findById(id);

        // Assert
        assertEquals(TipoUsuarioEnum.CLIENTE, resultado.getTipo());
    }
}