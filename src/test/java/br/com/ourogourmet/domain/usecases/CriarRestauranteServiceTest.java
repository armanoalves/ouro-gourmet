package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.exceptions.RestauranteDuplicadoException;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.usecases.CriarRestauranteUseCase.CriarRestauranteCommand;
import br.com.ourogourmet.domain.usecases.implementations.CriarRestauranteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarRestauranteServiceTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @InjectMocks
    private CriarRestauranteService criarRestauranteService;

    @Test
    void execute_ComRestauranteNovo_DeveCriarERetornarId() {
        // Arrange
        CriarRestauranteCommand command = new CriarRestauranteCommand(
                "Restaurante Teste",
                "Italiana",
                LocalTime.of(10, 0),
                LocalTime.of(22, 0));

        when(restauranteGateway.buscarPorNome(any())).thenReturn(Optional.empty());
        when(restauranteGateway.incluir(any())).thenReturn(1L);

        // Act
        Long id = criarRestauranteService.execute(command, restauranteGateway);

        // Assert
        assertEquals(1L, id);
        verify(restauranteGateway).buscarPorNome("Restaurante Teste");
        verify(restauranteGateway).incluir(any(Restaurante.class));
    }

    @Test
    void execute_ComRestauranteDuplicado_DeveLancarExcecao() {
        // Arrange
        CriarRestauranteCommand command = new CriarRestauranteCommand(
                "Restaurante Existente",
                "Italiana",
                LocalTime.of(10, 0),
                LocalTime.of(22, 0));

        Restaurante existente = new Restaurante();
        existente.setNome("Restaurante Existente");

        when(restauranteGateway.buscarPorNome(any())).thenReturn(Optional.of(existente));

        assertThrows(RestauranteDuplicadoException.class, () -> {
            criarRestauranteService.execute(command, restauranteGateway);
        });

        verify(restauranteGateway).buscarPorNome("Restaurante Existente");
        verify(restauranteGateway, never()).incluir(any());
    }

    @Test
    void execute_DeveCriarRestauranteComDadosCorretos() {
        // Arrange
        CriarRestauranteCommand command = new CriarRestauranteCommand(
                "Novo Restaurante",
                "Japonesa",
                LocalTime.of(11, 30),
                LocalTime.of(23, 0));

        when(restauranteGateway.buscarPorNome(any())).thenReturn(Optional.empty());
        when(restauranteGateway.incluir(any())).thenAnswer(invocation -> {
            Restaurante restaurante = invocation.getArgument(0);
            return 2L;
        });

        // Act
        Long id = criarRestauranteService.execute(command, restauranteGateway);

        // Assert
        verify(restauranteGateway).incluir(argThat(restaurante ->
                restaurante.getNome().equals("Novo Restaurante") &&
                        restaurante.getTipoCozinha().equals("Japonesa") &&
                        restaurante.getHorarioFuncionamentoDe().equals(LocalTime.of(11, 30)) &&
                        restaurante.getHorarioFuncionamentoAte().equals(LocalTime.of(23, 0)) &&
                        restaurante.getUsuario() == null
        ));
        assertEquals(2L, id);
    }
}