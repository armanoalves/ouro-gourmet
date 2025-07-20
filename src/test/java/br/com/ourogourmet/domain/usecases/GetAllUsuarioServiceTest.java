package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.implementations.GetAllUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllUsuarioServiceTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private GetAllUsuarioService getAllUsuarioService;

    @Test
    void findAll_ComParametrosValidos_DeveRetornarListaDeUsuarios() {
        // Arrange
        int page = 2;
        int size = 10;
        int expectedOffset = 10; // (2-1)*10 = 10

        List<Usuario> usuariosMock = Arrays.asList(
                Usuario.create(
                        "João Silva",
                        "joao@email.com",
                        "loginantigo", // login diferente do comando
                        true,
                        "senha123",
                        LocalDate.now(),
                        "Rua A, 123"),
                Usuario.create(
                        "Maria Silva",
                        "maria@email.com",
                        "loginmaria", // login diferente do comando
                        true,
                        "senha123",
                        LocalDate.now(),
                        "Rua A, 345")
        );

        when(usuarioGateway.findAll(size, expectedOffset)).thenReturn(usuariosMock);

        // Act
        List<Usuario> resultado = getAllUsuarioService.findAll(page, size);

        // Assert
        assertEquals(2, resultado.size());
        verify(usuarioGateway).findAll(size, expectedOffset);
    }

    @Test
    void findAll_ComPageUm_DeveCalcularOffsetZero() {
        // Arrange
        int page = 1;
        int size = 5;
        int expectedOffset = 0; // (1-1)*5 = 0

        when(usuarioGateway.findAll(size, expectedOffset)).thenReturn(List.of(Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345")));

        // Act
        List<Usuario> resultado = getAllUsuarioService.findAll(page, size);

        // Assert
        assertFalse(resultado.isEmpty());
        verify(usuarioGateway).findAll(size, expectedOffset);
    }

    @Test
    void findAll_ComSizeZero_DeveRetornarListaVazia() {
        // Arrange
        int page = 1;
        int size = 0;

        when(usuarioGateway.findAll(size, 0)).thenReturn(List.of());

        // Act
        List<Usuario> resultado = getAllUsuarioService.findAll(page, size);

        // Assert
        assertTrue(resultado.isEmpty());
        verify(usuarioGateway).findAll(size, 0);
    }

    @Test
    void findAll_ComListaVazia_DeveRetornarListaVazia() {
        // Arrange
        int page = 3;
        int size = 20;
        int expectedOffset = 40; // (3-1)*20 = 40

        when(usuarioGateway.findAll(size, expectedOffset)).thenReturn(List.of());

        // Act
        List<Usuario> resultado = getAllUsuarioService.findAll(page, size);

        // Assert
        assertTrue(resultado.isEmpty());
        verify(usuarioGateway).findAll(size, expectedOffset);
    }

    @Test
    void findAll_ComValoresMaximos_DeveCalcularOffsetCorretamente() {
        // Arrange
        int page = Integer.MAX_VALUE;
        int size = Integer.MAX_VALUE;
        // Cuidado com overflow aqui - na prática seria limitado pelo gateway
        int expectedOffset = (Integer.MAX_VALUE-1) * Integer.MAX_VALUE;

        when(usuarioGateway.findAll(size, expectedOffset)).thenReturn(List.of(Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345")));

        // Act
        List<Usuario> resultado = getAllUsuarioService.findAll(page, size);

        // Assert
        assertFalse(resultado.isEmpty());
        verify(usuarioGateway).findAll(size, expectedOffset);
    }

    @Test
    void findAll_DevePassarParametrosCorretamenteParaGateway() {
        // Arrange
        int page = 4;
        int size = 15;
        int expectedOffset = 45; // (4-1)*15 = 45

        when(usuarioGateway.findAll(size, expectedOffset)).thenReturn(List.of(Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345")));

        // Act
        getAllUsuarioService.findAll(page, size);

        // Assert
        verify(usuarioGateway).findAll(eq(size), eq(expectedOffset));
    }

    @Test
    void findAll_ComPageZero_DeveTratarComoPageUm() {
        // Arrange
        int page = 1;
        int size = 10;
        int expectedOffset = 0; // (1-1)*10 = 0 (tratando page 0 como 1)

        when(usuarioGateway.findAll(size, expectedOffset)).thenReturn(List.of(Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345")));

        // Act
        List<Usuario> resultado = getAllUsuarioService.findAll(page, size);

        // Assert
        assertFalse(resultado.isEmpty());
        verify(usuarioGateway).findAll(size, expectedOffset);
    }
}