package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.domain.exceptions.RestauranteNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.AlterarRestauranteUsuarioUseCase.AlterarRestauranteUsuarioCommand;
import br.com.ourogourmet.domain.usecases.implementations.AlterarRestauranteUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlterarRestauranteUsuarioServiceTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private AlterarRestauranteUsuarioService alterarRestauranteUsuarioService;

    @Test
    void execute_ComUsuarioValido_DeveAtualizarRestauranteComUsuario() {
        // Arrange
        Long restauranteId = 1L;
        String usuarioId = "user-123";
        AlterarRestauranteUsuarioCommand command = new AlterarRestauranteUsuarioCommand(restauranteId, usuarioId);

        Restaurante restaurante = new Restaurante();
        restaurante.setId(restauranteId);

        Usuario usuario = Usuario.create(
                "João Silva",
                "joao@email.com",
                "loginantigo", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 123");
        usuario.setId(usuarioId);

        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(Optional.of(restaurante));
        when(usuarioGateway.findById(usuarioId)).thenReturn(usuario);


        // Act
        alterarRestauranteUsuarioService.execute(command, restauranteGateway);

        // Assert
        verify(restauranteGateway).alterar(argThat(r ->
                r.getUsuario() != null &&
                        r.getUsuario().getId().equals(usuarioId)
        ));
    }

    @Test
    void execute_ComUsuarioNulo_DeveAtualizarRestauranteSemUsuario() {
        // Arrange
        Long restauranteId = 1L;
        AlterarRestauranteUsuarioCommand command = new AlterarRestauranteUsuarioCommand(restauranteId, null);

        Restaurante restaurante = new Restaurante();
        restaurante.setId(restauranteId);

        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(Optional.of(restaurante));

        // Act
        alterarRestauranteUsuarioService.execute(command, restauranteGateway);

        // Assert
        verify(restauranteGateway).alterar(argThat(r ->
                r.getUsuario() == null
        ));
    }

    @Test
    void execute_ComRestauranteNaoEncontrado_DeveLancarExcecao() {
        // Arrange
        Long restauranteId = 99L;
        String usuarioId = "user-123";
        AlterarRestauranteUsuarioCommand command = new AlterarRestauranteUsuarioCommand(restauranteId, usuarioId);

        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(Optional.empty());

        // Act & Assert
        RestauranteNaoEncontradoException exception = assertThrows(RestauranteNaoEncontradoException.class, () -> {
            alterarRestauranteUsuarioService.execute(command, restauranteGateway);
        });

        assertEquals("Restaurante não encontrado: 99", exception.getMessage());
        verify(restauranteGateway, never()).alterar(any());
    }

    @Test
    void execute_ComUsuarioInexistente_DeveAtualizarComUsuarioNulo() {
        // Arrange
        Long restauranteId = 1L;
        String usuarioId = "user-404";
        AlterarRestauranteUsuarioCommand command = new AlterarRestauranteUsuarioCommand(restauranteId, usuarioId);

        Restaurante restaurante = new Restaurante();
        restaurante.setId(restauranteId);

        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(Optional.of(restaurante));
        when(usuarioGateway.findById(usuarioId)).thenReturn(null);

        // Act
        alterarRestauranteUsuarioService.execute(command, restauranteGateway);

        // Assert
        verify(restauranteGateway).alterar(argThat(r ->
                r.getUsuario() == null
        ));
    }

    @Test
    void execute_DeveManterDadosOriginaisDoRestaurante() {
        // Arrange
        Long restauranteId = 1L;
        String usuarioId = "user-123";
        AlterarRestauranteUsuarioCommand command = new AlterarRestauranteUsuarioCommand(restauranteId, usuarioId);

        Restaurante restaurante = new Restaurante();
        restaurante.setId(restauranteId);
        restaurante.setNome("Restaurante Teste");
        restaurante.setTipoCozinha("Italiana");
        restaurante.setHorarioFuncionamento(LocalTime.of(10, 0),LocalTime.of(22, 0));

        Usuario usuario = Usuario.create(
                "João Silva",
                "joao@email.com",
                "loginantigo", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 123");
        usuario.setId(usuarioId);

        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(Optional.of(restaurante));
        when(usuarioGateway.findById(usuarioId)).thenReturn(usuario);

        // Act
        alterarRestauranteUsuarioService.execute(command, restauranteGateway);

        // Assert
        verify(restauranteGateway).alterar(argThat(r ->
                r.getNome().equals("Restaurante Teste") &&
                        r.getTipoCozinha().equals("Italiana") &&
                        r.getHorarioFuncionamentoDe().equals(LocalTime.of(10, 0)) &&
                        r.getHorarioFuncionamentoAte().equals(LocalTime.of(22, 0)) &&
                        r.getUsuario().getId().equals(usuarioId)
        ));
    }
}
