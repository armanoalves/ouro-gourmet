package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.exceptions.RestauranteNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.usecases.implementations.GetByIdRestauranteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetByIdRestauranteServiceTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @InjectMocks
    private GetByIdRestauranteService getByIdRestauranteService;

    @Test
    void execute_ComIdExistente_DeveRetornarRestaurante() {
        // Arrange
        Long id = 1L;
        Restaurante restauranteMock = new Restaurante();
        restauranteMock.setId(id);

        when(restauranteGateway.buscarPorId(id)).thenReturn(Optional.of(restauranteMock));

        // Act
        Restaurante resultado = getByIdRestauranteService.execute(id, restauranteGateway);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(restauranteGateway).buscarPorId(id);
    }

    @Test
    void execute_ComIdNaoExistente_DeveLancarExcecao() {
        // Arrange
        Long id = 99L;
        when(restauranteGateway.buscarPorId(id)).thenReturn(Optional.empty());

        // Act & Assert
        RestauranteNaoEncontradoException exception = assertThrows(
                RestauranteNaoEncontradoException.class,
                () -> getByIdRestauranteService.execute(id, restauranteGateway)
        );

        assertEquals("Restaurante não encontrado: " + id, exception.getMessage());
        verify(restauranteGateway).buscarPorId(id);
    }

    @Test
    void execute_ComIdNulo_DeveLancarExcecao() {
        // Act & Assert
        assertThrows(
                NullPointerException.class,
                () -> getByIdRestauranteService.execute(null, restauranteGateway)
        );
    }

    @Test
    void execute_DeveChamarGatewayApenasUmaVez() {
        // Arrange
        Long id = 1L;
        Restaurante restauranteMock = new Restaurante();
        when(restauranteGateway.buscarPorId(id)).thenReturn(Optional.of(restauranteMock));

        // Act
        getByIdRestauranteService.execute(id, restauranteGateway);

        // Assert
        verify(restauranteGateway, times(1)).buscarPorId(id);
        verifyNoMoreInteractions(restauranteGateway);
    }

    @Test
    void execute_ComDiferentesTiposDeIds_DeveComportarCorretamente() {
        // Teste com ID mínimo
        Long idMinimo = 1L;
        Restaurante restaurante1 = new Restaurante();
        when(restauranteGateway.buscarPorId(idMinimo)).thenReturn(Optional.of(restaurante1));
        assertDoesNotThrow(() -> getByIdRestauranteService.execute(idMinimo, restauranteGateway));

        // Teste com ID máximo
        Long idMaximo = Long.MAX_VALUE;
        Restaurante restaurante2 = new Restaurante();
        when(restauranteGateway.buscarPorId(idMaximo)).thenReturn(Optional.of(restaurante2));
        assertDoesNotThrow(() -> getByIdRestauranteService.execute(idMaximo, restauranteGateway));

        verify(restauranteGateway, times(2)).buscarPorId(any());
    }

    @Test
    void execute_DeveRetornarRestauranteCompleto() {
        // Arrange
        Long id = 1L;
        Restaurante restauranteMock = new Restaurante();
        restauranteMock.setId(id);
        restauranteMock.setNome("Restaurante Teste");
        restauranteMock.setTipoCozinha("Italiana");

        when(restauranteGateway.buscarPorId(id)).thenReturn(Optional.of(restauranteMock));

        // Act
        Restaurante resultado = getByIdRestauranteService.execute(id, restauranteGateway);

        // Assert
        assertNotNull(resultado);
        assertEquals("Restaurante Teste", resultado.getNome());
        assertEquals("Italiana", resultado.getTipoCozinha());
    }
}