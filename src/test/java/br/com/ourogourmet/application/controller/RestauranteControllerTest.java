package br.com.ourogourmet.application.controller;

import br.com.ourogourmet.application.controller.dtos.AlterarRestauranteDTO;
import br.com.ourogourmet.application.controller.dtos.AlterarUsuarioRestauranteDTO;
import br.com.ourogourmet.application.controller.dtos.CriarRestauranteDTO;
import br.com.ourogourmet.application.presenter.RestaurantePresenter;
import br.com.ourogourmet.application.presenter.RestauranteResponse;
import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.exceptions.RestauranteCamposInvalidosException;
import br.com.ourogourmet.domain.usecases.*;
import br.com.ourogourmet.infrastructure.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestauranteControllerTest {

    @Mock
    private CriarRestauranteUseCase criarRestauranteUseCase;

    @Mock
    private AlterarRestauranteUseCase alterarRestauranteUseCase;

    @Mock
    private DeletarRestauranteUseCase deletarRestauranteUseCase;

    @Mock
    private GetByIdRestauranteUseCase getByIdRestauranteUseCase;

    @Mock
    private GetAllRestauranteUseCase getAllRestaurantesUseCase;

    @Mock
    private AlterarRestauranteUsuarioUseCase alterarRestauranteUsuarioUseCase;

    @Mock
    private RestaurantePresenter restaurantePresenter;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private RestauranteController restauranteController;

    private CriarRestauranteDTO criarRestauranteDTO;
    private AlterarRestauranteDTO alterarRestauranteDTO;
    private AlterarUsuarioRestauranteDTO alterarUsuarioRestauranteDTO;
    private RestauranteResponse restauranteResponse;

    @BeforeEach
    void setUp() {
        criarRestauranteDTO = new CriarRestauranteDTO(
                "Restaurante Teste",
                "Italiana",
                "10:00",
                "22:00");

        alterarRestauranteDTO = new AlterarRestauranteDTO(
                "Restaurante Teste Atualizado",
                "Italiana",
                "11:00",
                "23:00"
                );

        alterarUsuarioRestauranteDTO = new AlterarUsuarioRestauranteDTO("2");

        restauranteResponse = new RestauranteResponse(
                1L,
                "Restaurante Teste",
                "Italiana",
                LocalTime.of(10, 0),
                LocalTime.of(22, 0),
                null);
    }

    @Test
    void criarRestaurante_ComDadosValidos_DeveRetornarCreated() {
        // Arrange
        when(validator.validateObject(any())).thenReturn(new BeanPropertyBindingResult(criarRestauranteDTO, "criarRestauranteDTO"));
        when(criarRestauranteUseCase.execute(any(), any())).thenReturn(1L);

        // Act
        ResponseEntity<Long> response = restauranteController.criarRestaurante(criarRestauranteDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(criarRestauranteUseCase).execute(any(), any());
    }

    @Test
    void criarRestaurante_ComDadosInvalidos_DeveLancarExcecao() {
        // Arrange
        Errors errors = new BeanPropertyBindingResult(criarRestauranteDTO, "criarRestauranteDTO");
        errors.reject("nome", "Nome é obrigatório");

        when(validator.validateObject(any())).thenReturn(errors);

        // Act & Assert
        assertThrows(RestauranteCamposInvalidosException.class, () -> {
            restauranteController.criarRestaurante(criarRestauranteDTO);
        });
    }

    @Test
    void alterarRestaurante_ComDadosValidos_DeveRetornarNoContent() {
        // Arrange
        Long id = 1L;
        when(validator.validateObject(any())).thenReturn(new BeanPropertyBindingResult(alterarRestauranteDTO, "alterarRestauranteDTO"));

        // Act
        ResponseEntity<Void> response = restauranteController.alterarRestaurante(id, alterarRestauranteDTO);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(alterarRestauranteUseCase).execute(any(), any());
    }

    @Test
    void alterarRestaurante_ComDadosInvalidos_DeveLancarExcecao() {
        // Arrange
        Long id = 1L;
        Errors errors = new BeanPropertyBindingResult(alterarRestauranteDTO, "alterarRestauranteDTO");
        errors.reject("nome", "Nome é obrigatório");

        when(validator.validateObject(any())).thenReturn(errors);

        // Act & Assert
        assertThrows(RestauranteCamposInvalidosException.class, () -> {
            restauranteController.alterarRestaurante(id, alterarRestauranteDTO);
        });
    }

    @Test
    void alterarRestauranteUsuario_DeveRetornarNoContent() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<Void> response = restauranteController.alterarRestauranteUsuario(id, alterarUsuarioRestauranteDTO);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(alterarRestauranteUsuarioUseCase).execute(any(), any());
    }

    @Test
    void deletarRestaurante_DeveRetornarNoContent() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<Void> response = restauranteController.deletarRestaurante(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deletarRestauranteUseCase).execute(eq(id), any());
    }

    @Test
    void buscarTodosRestaurantes_DeveRetornarListaRestaurantes() {
        // Arrange
        int page = 1;
        int size = 10;
        when(getAllRestaurantesUseCase.execute(anyInt(), anyInt(), any())).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<RestauranteResponse>> response = restauranteController.buscarTodosRestaurantes(page, size);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(getAllRestaurantesUseCase).execute(eq(page), eq(size), any());
    }

    @Test
    void buscarRestaurantePorId_DeveRetornarRestaurante() {
        // Arrange
        Long id = 1L;
        when(getByIdRestauranteUseCase.execute(anyLong(), any())).thenReturn(new Restaurante());
        when(restaurantePresenter.presenter(any())).thenReturn(restauranteResponse);

        // Act
        ResponseEntity<RestauranteResponse> response = restauranteController.buscarRestaurantePorId(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(getByIdRestauranteUseCase).execute(eq(id), any());
    }


}
