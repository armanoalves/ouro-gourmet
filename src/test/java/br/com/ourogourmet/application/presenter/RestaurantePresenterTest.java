package br.com.ourogourmet.application.presenter;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.entities.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantePresenterTest {

    @InjectMocks
    private RestaurantePresenter restaurantePresenter;

    @Test
    void presenter_ComRestauranteCompleto_DeveRetornarResponseCompleto() {
        // Arrange
        Usuario usuario = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senhaAntiga",
                LocalDate.now(),
                "Rua A, 345",
                1L);

        usuario.setId("user-123");
        usuario.setNome("João Silva");
        usuario.setEmail("joao@email.com");
        usuario.setLogin("joaosilva");
        usuario.setAtivo(true);
        usuario.setEndereco("Rua A, 123");
        usuario.setTipoUsuarioId(1L);

        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNome("Restaurante Teste");
        restaurante.setTipoCozinha("Italiana");
        restaurante.setHorarioFuncionamento(LocalTime.of(10, 0),LocalTime.of(22, 0));
        restaurante.setUsuario(usuario);

        // Act
        RestauranteResponse response = restaurantePresenter.presenter(restaurante);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Restaurante Teste", response.getNome());
        assertEquals("Italiana", response.getTipoCozinha());
        assertEquals(LocalTime.of(10, 0), response.getHorarioFuncionamentoDe());
        assertEquals(LocalTime.of(22, 0), response.getHorarioFuncionamentoAte());

        assertNotNull(response.getUsuario());
        assertEquals("user-123", response.getUsuario().getId());
        assertEquals("João Silva", response.getUsuario().getNome());
        assertEquals("joao@email.com", response.getUsuario().getEmail());
        assertEquals("joaosilva", response.getUsuario().getLogin());
        assertTrue(response.getUsuario().getAtivo());
        assertEquals("Rua A, 123", response.getUsuario().getEndereco());
        assertEquals(1L, response.getUsuario().getTipoUsuarioId());
    }

    @Test
    void presenter_ComRestauranteSemUsuario_DeveRetornarResponseComUsuarioNulo() {
        // Arrange
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNome("Restaurante Teste");
        restaurante.setTipoCozinha("Italiana");
        restaurante.setHorarioFuncionamento(LocalTime.of(10, 0),LocalTime.of(22, 0));
        restaurante.setUsuario(null);

        // Act
        RestauranteResponse response = restaurantePresenter.presenter(restaurante);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Restaurante Teste", response.getNome());
        assertEquals("Italiana", response.getTipoCozinha());
        assertEquals(LocalTime.of(10, 0), response.getHorarioFuncionamentoDe());
        assertEquals(LocalTime.of(22, 0), response.getHorarioFuncionamentoAte());
        assertNull(response.getUsuario());
    }

    @Test
    void presenter_ComRestauranteParcial_DeveRetornarResponseParcial() {
        // Arrange
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNome("Restaurante Teste");
        // Campos não obrigatórios deixados como null

        // Act
        RestauranteResponse response = restaurantePresenter.presenter(restaurante);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Restaurante Teste", response.getNome());
        assertNull(response.getTipoCozinha());
        assertNull(response.getHorarioFuncionamentoDe());
        assertNull(response.getHorarioFuncionamentoAte());
        assertNull(response.getUsuario());
    }

    @Test
    void presenter_ComUsuarioParcial_DeveRetornarUsuarioResponseParcial() {
        // Arrange
        Usuario usuario = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senhaAntiga",
                LocalDate.now(),
                "Rua A, 345",
                1L);

        usuario.setId("user-123");
        usuario.setNome("Maria Silva");
        // Campos não obrigatórios deixados como null

        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setUsuario(usuario);

        // Act
        RestauranteResponse response = restaurantePresenter.presenter(restaurante);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getUsuario());
        assertEquals("user-123", response.getUsuario().getId());
        assertEquals("Maria Silva", response.getUsuario().getNome());

    }

    @Test
    void presenter_ComRestauranteNulo_DeveLancarExcecao() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            restaurantePresenter.presenter(null);
        });
    }
}