package br.com.ourogourmet.infrastructure.adapter.repository;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.infrastructure.model.RestauranteEntity;
import br.com.ourogourmet.infrastructure.repository.RestauranteEntityRepository;
import br.com.ourogourmet.infrastructure.repository.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteEntityRepositoryTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private RestauranteEntityRepository restauranteEntityRepository;

    @Test
    void incluir_DeveSalvarERetornarId() {
        // Arrange
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Restaurante Teste");

        RestauranteEntity entity = new RestauranteEntity(restaurante);
        entity.setId(1L);

        when(restauranteRepository.save(any(RestauranteEntity.class))).thenReturn(entity);

        // Act
        Long id = restauranteEntityRepository.incluir(restaurante);

        // Assert
        assertEquals(1L, id);
        verify(restauranteRepository).save(any(RestauranteEntity.class));
    }

    @Test
    void buscarPorId_ComIdExistente_DeveRetornarRestaurante() {

        var restaurante = Restaurante.create("BALCON","URUGUAIA",
                LocalTime.now(),LocalTime.now().plusHours(8),Usuario.create(
                        "Maria Silva",
                        "maria@email.com",
                        "loginmaria", // login diferente do comando
                        true,
                        "senha123",
                        LocalDate.now(),
                        "Rua A, 345"));

        // Arrange
        Long id = 1L;
        RestauranteEntity entity = new RestauranteEntity(restaurante);
        entity.setId(id);

        when(restauranteRepository.findById(id)).thenReturn(Optional.of(entity));

        // Act
        Optional<Restaurante> result = restauranteEntityRepository.buscarPorId(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(restauranteRepository).findById(id);
    }

    @Test
    void buscarPorId_ComIdNaoExistente_DeveRetornarVazio() {
        // Arrange
        Long id = 99L;
        when(restauranteRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Restaurante> result = restauranteEntityRepository.buscarPorId(id);

        // Assert
        assertTrue(result.isEmpty());
        verify(restauranteRepository).findById(id);
    }

    @Test
    void buscarPorNome_ComNomeExistente_DeveRetornarRestaurante() {
        // Arrange
        String nome = "Restaurante Teste";
        var restaurante = Restaurante.create(nome,"URUGUAIA",
                LocalTime.now(),LocalTime.now().plusHours(8),Usuario.create(
                        "Maria Silva",
                        "maria@email.com",
                        "loginmaria", // login diferente do comando
                        true,
                        "senha123",
                        LocalDate.now(),
                        "Rua A, 345"));
        RestauranteEntity entity = new RestauranteEntity(restaurante);

        when(restauranteRepository.findByNome(nome)).thenReturn(Optional.of(entity));

        // Act
        Optional<Restaurante> result = restauranteEntityRepository.buscarPorNome(nome);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(nome, result.get().getNome());
        verify(restauranteRepository).findByNome(nome);
    }

    @Test
    void buscarTodos_DeveRetornarListaPaginada() {
        // Arrange
        int page = 1;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page-1, size);

        var restaurante1 = Restaurante.create("BALCON","URUGUAIA",
                LocalTime.now(),LocalTime.now().plusHours(8),Usuario.create(
                        "Maria Silva",
                        "maria@email.com",
                        "loginmaria", // login diferente do comando
                        true,
                        "senha123",
                        LocalDate.now(),
                        "Rua A, 345"));

        RestauranteEntity entity1 = new RestauranteEntity(restaurante1);

        var restaurante2 = Restaurante.create("JOJO","LAMEN",
                LocalTime.now(),LocalTime.now().plusHours(8),Usuario.create(
                        "Maria Silva",
                        "maria@email.com",
                        "loginmaria", // login diferente do comando
                        true,
                        "senha123",
                        LocalDate.now(),
                        "Rua A, 345"));

        RestauranteEntity entity2 = new RestauranteEntity(restaurante2);
        Page<RestauranteEntity> pageResult = new PageImpl<>(List.of(entity1, entity2));

        when(restauranteRepository.findAll(pageRequest)).thenReturn(pageResult);

        // Act
        List<Restaurante> result = restauranteEntityRepository.buscarTodos(page, size);

        // Assert
        assertEquals(2, result.size());
        verify(restauranteRepository).findAll(pageRequest);
    }

    @Test
    void alterar_DeveAtualizarRestauranteExistente() {
        // Arrange
        Long id = 1L;
        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);
        restaurante.setNome("Novo Nome");
        restaurante.setTipoCozinha("Italiana");
        restaurante.setHorarioFuncionamento(LocalTime.of(10, 0),LocalTime.of(22, 0));

        RestauranteEntity existingEntity = new RestauranteEntity();
        existingEntity.setId(id);

        when(restauranteRepository.findById(id)).thenReturn(Optional.of(existingEntity));
        when(restauranteRepository.save(any(RestauranteEntity.class))).thenReturn(existingEntity);

        // Act
        restauranteEntityRepository.alterar(restaurante);

        // Assert
        verify(restauranteRepository).findById(id);
        verify(restauranteRepository).save(argThat(e ->
                e.getNome().equals("Novo Nome") &&
                        e.getTipoCozinha().equals("Italiana") &&
                        e.getHorarioFuncionamentoDe().equals(LocalTime.of(10, 0)) &&
                        e.getHorarioFuncionamentoAte().equals(LocalTime.of(22, 0))
        ));
    }

    @Test
    void alterarUsuario_DeveAtualizarApenasUsuario() {
        // Arrange
        Long id = 1L;
        Usuario usuario = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345");
        usuario.setId("user-123");

        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);
        restaurante.setUsuario(usuario);

        RestauranteEntity existingEntity = new RestauranteEntity();
        existingEntity.setId(id);
        existingEntity.setNome("Restaurante Original");

        when(restauranteRepository.findById(id)).thenReturn(Optional.of(existingEntity));
        when(restauranteRepository.save(any(RestauranteEntity.class))).thenReturn(existingEntity);

        // Act
        restauranteEntityRepository.alterarUsuario(restaurante);

        // Assert
        verify(restauranteRepository).findById(id);
        verify(restauranteRepository).save(argThat(e ->
                e.getUsuario() != null &&
                        e.getUsuario().getId().equals("user-123") &&
                        e.getNome().equals("Restaurante Original") // Nome não deve mudar
        ));
    }

    @Test
    void deletar_ComRestauranteExistente_DeveRemover() {
        // Arrange
        Long id = 1L;
        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);

        RestauranteEntity entity = new RestauranteEntity();
        entity.setId(id);

        when(restauranteRepository.findById(id)).thenReturn(Optional.of(entity));

        // Act
        restauranteEntityRepository.deletar(restaurante);

        // Assert
        verify(restauranteRepository).findById(id);
        verify(restauranteRepository).delete(entity);
    }

    @Test
    void deletar_ComRestauranteNaoExistente_NaoDeveRemover() {
        // Arrange
        Long id = 99L;
        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);

        when(restauranteRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        restauranteEntityRepository.deletar(restaurante);

        // Assert
        verify(restauranteRepository).findById(id);
        verify(restauranteRepository, never()).delete(any());
    }
}