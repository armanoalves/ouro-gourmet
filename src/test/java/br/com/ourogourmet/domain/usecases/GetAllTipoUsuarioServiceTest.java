package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.gateway.TipoUsuarioGateway;
import br.com.ourogourmet.domain.usecases.implementations.GetAllTipoUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllTipoUsuarioServiceTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private GetAllTipoUsuarioService getAllTipoUsuarioService;

    @Test
    void findAll_ComDadosValidos_DeveRetornarLista() {
        // Arrange
        int page = 1;
        int size = 10;
        int offset = 0;

        List<TipoUsuario> mockList = Arrays.asList(
                TipoUsuario.create("DONO"),
                TipoUsuario.create("CLIENTE")
        );

        when(tipoUsuarioGateway.findAll(size, offset)).thenReturn(mockList);

        // Act
        List<TipoUsuario> resultado = getAllTipoUsuarioService.findAll(page, size);

        // Assert
        assertEquals(2, resultado.size());
        verify(tipoUsuarioGateway).findAll(size, offset);
    }

    @Test
    void findAll_ComPaginaMaiorQueUm_DeveCalcularOffsetCorretamente() {
        // Arrange
        int page = 3;
        int size = 5;
        int offset = 10;

        when(tipoUsuarioGateway.findAll(size, offset)).thenReturn(List.of());

        // Act
        List<TipoUsuario> resultado = getAllTipoUsuarioService.findAll(page, size);

        // Assert
        assertTrue(resultado.isEmpty());
        verify(tipoUsuarioGateway).findAll(size, offset);
    }

    @Test
    void findAll_ComListaVazia_DeveRetornarListaVazia() {
        // Arrange
        int page = 1;
        int size = 10;

        when(tipoUsuarioGateway.findAll(size, 0)).thenReturn(List.of());

        // Act
        List<TipoUsuario> resultado = getAllTipoUsuarioService.findAll(page, size);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(tipoUsuarioGateway).findAll(size, 0);
    }
}