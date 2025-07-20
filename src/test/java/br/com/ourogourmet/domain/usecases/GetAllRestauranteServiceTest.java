package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.usecases.implementations.GetAllRestauranteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllRestauranteServiceTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @InjectMocks
    private GetAllRestauranteService getAllRestauranteService;

    @Test
    void execute_ComParametrosValidos_DeveRetornarListaDeRestaurantes() {
        // Arrange
        int page = 1;
        int size = 10;
        List<Restaurante> restaurantesMock = Arrays.asList(
                new Restaurante(),
                new Restaurante()
        );

        when(restauranteGateway.buscarTodos(page, size)).thenReturn(restaurantesMock);

        // Act
        List<Restaurante> resultado = getAllRestauranteService.execute(page, size, restauranteGateway);

        // Assert
        assertEquals(2, resultado.size());
        verify(restauranteGateway).buscarTodos(page, size);
    }

    @Test
    void execute_ComPageZero_DeveRetornarPrimeiraPagina() {
        // Arrange
        int page = 0;
        int size = 10;
        List<Restaurante> restaurantesMock = Arrays.asList(new Restaurante());

        when(restauranteGateway.buscarTodos(page, size)).thenReturn(restaurantesMock);

        // Act
        List<Restaurante> resultado = getAllRestauranteService.execute(page, size, restauranteGateway);

        // Assert
        assertFalse(resultado.isEmpty());
        verify(restauranteGateway).buscarTodos(page, size);
    }

    @Test
    void execute_ComSizeZero_DeveRetornarListaVazia() {
        // Arrange
        int page = 1;
        int size = 0;

        when(restauranteGateway.buscarTodos(page, size)).thenReturn(List.of());

        // Act
        List<Restaurante> resultado = getAllRestauranteService.execute(page, size, restauranteGateway);

        // Assert
        assertTrue(resultado.isEmpty());
        verify(restauranteGateway).buscarTodos(page, size);
    }

    @Test
    void execute_ComListaVazia_DeveRetornarListaVazia() {
        // Arrange
        int page = 1;
        int size = 10;

        when(restauranteGateway.buscarTodos(page, size)).thenReturn(List.of());

        // Act
        List<Restaurante> resultado = getAllRestauranteService.execute(page, size, restauranteGateway);

        // Assert
        assertTrue(resultado.isEmpty());
        verify(restauranteGateway).buscarTodos(page, size);
    }

    @Test
    void execute_ComValoresMaximos_DeveChamarGatewayCorretamente() {
        // Arrange
        int page = Integer.MAX_VALUE;
        int size = Integer.MAX_VALUE;
        List<Restaurante> restaurantesMock = Arrays.asList(new Restaurante());

        when(restauranteGateway.buscarTodos(page, size)).thenReturn(restaurantesMock);

        // Act
        List<Restaurante> resultado = getAllRestauranteService.execute(page, size, restauranteGateway);

        // Assert
        assertFalse(resultado.isEmpty());
        verify(restauranteGateway).buscarTodos(page, size);
    }

    @Test
    void execute_DevePassarParametrosCorretamente() {
        // Arrange
        int page = 2;
        int size = 5;
        List<Restaurante> restaurantesMock = Arrays.asList(new Restaurante());

        when(restauranteGateway.buscarTodos(page, size)).thenReturn(restaurantesMock);

        // Act
        getAllRestauranteService.execute(page, size, restauranteGateway);

        // Assert
        verify(restauranteGateway).buscarTodos(eq(page), eq(size));
    }
}