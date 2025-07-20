package br.com.ourogourmet.domain.usecases;


import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.exceptions.RestauranteNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.usecases.implementations.DeletarRestauranteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarRestauranteServiceTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @InjectMocks
    private DeletarRestauranteService deletarRestauranteService;

    @Test
    void execute_ComRestauranteExistente_DeveDeletarComSucesso() {
        // Arrange
        Long id = 1L;
        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);

        when(restauranteGateway.buscarPorId(id)).thenReturn(Optional.of(restaurante));
        doNothing().when(restauranteGateway).deletar(any(Restaurante.class));

        // Act
        deletarRestauranteService.execute(id, restauranteGateway);

        // Assert
        verify(restauranteGateway).buscarPorId(id);
        verify(restauranteGateway).deletar(restaurante);
    }

    @Test
    void execute_ComRestauranteNaoEncontrado_DeveLancarExcecao() {
        // Arrange
        Long id = 99L;
        when(restauranteGateway.buscarPorId(id)).thenReturn(Optional.empty());

        // Act & Assert
        RestauranteNaoEncontradoException exception = assertThrows(RestauranteNaoEncontradoException.class, () -> {
            deletarRestauranteService.execute(id, restauranteGateway);
        });

        assertEquals("Restaurante não encontrado: 99", exception.getMessage());
        verify(restauranteGateway, never()).deletar(any());
    }

    @Test
    void execute_DeveChamarMetodosNaOrdemCorreta() {
        // Arrange
        Long id = 1L;
        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);

        when(restauranteGateway.buscarPorId(id)).thenReturn(Optional.of(restaurante));
        doNothing().when(restauranteGateway).deletar(any(Restaurante.class));

        // Act
        deletarRestauranteService.execute(id, restauranteGateway);

        // Assert
        InOrder inOrder = inOrder(restauranteGateway);
        inOrder.verify(restauranteGateway).buscarPorId(id);
        inOrder.verify(restauranteGateway).deletar(restaurante);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void execute_DevePassarORestauranteCorretoParaDelecao() {
        // Arrange
        Long id = 1L;
        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);
        restaurante.setNome("Restaurante Teste");

        when(restauranteGateway.buscarPorId(id)).thenReturn(Optional.of(restaurante));
        doNothing().when(restauranteGateway).deletar(any(Restaurante.class));

        // Act
        deletarRestauranteService.execute(id, restauranteGateway);

        // Assert
        verify(restauranteGateway).deletar(argThat(r ->
                r.getId().equals(id) &&
                        r.getNome().equals("Restaurante Teste")
        ));
    }

    @Test
    void execute_ComDiferentesTiposDeIds_DeveLancarExcecaoQuandoNaoEncontrado() {
        // Arrange
        Long id = 999L;
        when(restauranteGateway.buscarPorId(id)).thenReturn(Optional.empty());

        // Act & Assert
        RestauranteNaoEncontradoException exception = assertThrows(RestauranteNaoEncontradoException.class, () -> {
            deletarRestauranteService.execute(id, restauranteGateway);
        });

        assertEquals("Restaurante não encontrado: 999", exception.getMessage());
    }
}