package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.domain.exceptions.RestauranteNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.AlterarRestauranteUseCase.AlterarRestauranteCommand;
import br.com.ourogourmet.domain.usecases.implementations.AlterarRestauranteService;
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
class AlterarRestauranteServiceTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private AlterarRestauranteService alterarRestauranteService;

    @Test
    void execute_ComDadosValidos_DeveAtualizarRestaurante() {
        // Arrange
        Long restauranteId = 1L;
        String usuarioId = "1";
        AlterarRestauranteCommand command = new AlterarRestauranteCommand(
                restauranteId,
                "Novo Nome",
                "Japonesa",
                LocalTime.of(11, 0),
                LocalTime.of(23, 0));

        Restaurante restauranteExistente = new Restaurante();
        restauranteExistente.setId(restauranteId);
        restauranteExistente.setNome("Nome Antigo");

        Usuario usuario = Usuario.create("João Silva","joao@email.com","login",true,"senha", LocalDate.now(),"Rua da Glória, 355", 1L);
        usuario.setId(usuarioId);

        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(Optional.of(restauranteExistente));
        when(restauranteGateway.buscarPorNome("Novo Nome")).thenReturn(Optional.empty());

        // Act
        alterarRestauranteService.execute(command, restauranteGateway);

        // Assert
        verify(restauranteGateway).alterar(argThat(restaurante ->
                restaurante.getNome().equals("Novo Nome") &&
                        restaurante.getTipoCozinha().equals("Japonesa") &&
                        restaurante.getHorarioFuncionamentoDe().equals(LocalTime.of(11, 0)) &&
                        restaurante.getHorarioFuncionamentoAte().equals(LocalTime.of(23, 0))
        ));
    }

    @Test
    void execute_ComRestauranteNaoExistente_DeveLancarExcecao() {
        // Arrange
        Long restauranteId = 99L;
        AlterarRestauranteCommand command = new AlterarRestauranteCommand(
                restauranteId,
                "Nome Qualquer",
                "Tipo Qualquer",
                LocalTime.of(10, 0),
                LocalTime.of(22, 0));

        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(Optional.empty());

        // Act & Assert
        RestauranteNaoEncontradoException exception = assertThrows(RestauranteNaoEncontradoException.class, () -> {
            alterarRestauranteService.execute(command, restauranteGateway);
        });

        assertEquals("Restaurante não encontrado: 99", exception.getMessage());
        verify(restauranteGateway, never()).alterar(any());
    }

    @Test
    void execute_ComNomeDuplicado_DeveLancarExcecao() {
        // Arrange
        Long restauranteId = 1L;
        Long outroRestauranteId = 2L;
        AlterarRestauranteCommand command = new AlterarRestauranteCommand(
                restauranteId,
                "Nome Duplicado",
                "Italiana",
                LocalTime.of(10, 0),
                LocalTime.of(22, 0));

        Restaurante restauranteExistente = new Restaurante();
        restauranteExistente.setId(restauranteId);

        Restaurante restauranteComNomeDuplicado = new Restaurante();
        restauranteComNomeDuplicado.setId(outroRestauranteId);

        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(Optional.of(restauranteExistente));
        when(restauranteGateway.buscarPorNome("Nome Duplicado")).thenReturn(Optional.of(restauranteComNomeDuplicado));

        // Act & Assert
        RestauranteNaoEncontradoException exception = assertThrows(RestauranteNaoEncontradoException.class, () -> {
            alterarRestauranteService.execute(command, restauranteGateway);
        });

        assertEquals("Restaurante não encontrado: Nome Duplicado", exception.getMessage());
        verify(restauranteGateway, never()).alterar(any());
    }

    @Test
    void execute_ComMesmoNome_DeveAtualizarRestaurante() {
        // Arrange
        Long restauranteId = 1L;
        AlterarRestauranteCommand command = new AlterarRestauranteCommand(
                restauranteId,
                "Mesmo Nome",
                "Italiana",
                LocalTime.of(10, 0),
                LocalTime.of(22, 0));

        Restaurante restauranteExistente = new Restaurante();
        restauranteExistente.setId(restauranteId);
        restauranteExistente.setNome("Mesmo Nome");

        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(Optional.of(restauranteExistente));
        when(restauranteGateway.buscarPorNome("Mesmo Nome")).thenReturn(Optional.of(restauranteExistente));

        // Act
        alterarRestauranteService.execute(command, restauranteGateway);

        // Assert
        verify(restauranteGateway).alterar(any());
    }

    @Test
    void execute_SemUsuario_DeveAtualizarComUsuarioNulo() {
        // Arrange
        Long restauranteId = 1L;
        AlterarRestauranteCommand command = new AlterarRestauranteCommand(
                restauranteId,
                "Restaurante Sem Usuário",
                "Italiana",
                LocalTime.of(10, 0),
                LocalTime.of(22, 0));

        Restaurante restauranteExistente = new Restaurante();
        restauranteExistente.setId(restauranteId);

        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(Optional.of(restauranteExistente));
        when(restauranteGateway.buscarPorNome("Restaurante Sem Usuário")).thenReturn(Optional.empty());

        // Act
        alterarRestauranteService.execute(command, restauranteGateway);

        // Assert
        verify(restauranteGateway).alterar(argThat(restaurante ->
                restaurante.getUsuario() == null
        ));
    }


}