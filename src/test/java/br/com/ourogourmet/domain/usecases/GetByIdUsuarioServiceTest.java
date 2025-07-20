package br.com.ourogourmet.domain.usecases;


import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.implementations.GetByIdUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetByIdUsuarioServiceTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private GetByIdUsuarioService getByIdUsuarioService;

    @Test
    void findById_ComIdExistente_DeveRetornarUsuario() {
        // Arrange
        String id = "user-123";
        Usuario usuarioMock = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345");

        usuarioMock.setId(id);

        when(usuarioGateway.findById(id)).thenReturn(usuarioMock);

        // Act
        Usuario resultado = getByIdUsuarioService.findById(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(usuarioGateway).findById(id);
    }

    @Test
    void findById_ComIdNaoExistente_DeveRetornarNulo() {
        // Arrange
        String id = "user-404";
        when(usuarioGateway.findById(id)).thenReturn(null);

        // Act
        Usuario resultado = getByIdUsuarioService.findById(id);

        // Assert
        assertNull(resultado);
        verify(usuarioGateway).findById(id);
    }

    @Test
    void findById_ComIdNulo_DeveRetornarNulo() {
        // Act
        Usuario resultado = getByIdUsuarioService.findById(null);

        // Assert
        assertNull(resultado);
    }

    @Test
    void findById_DeveChamarGatewayApenasUmaVez() {
        // Arrange
        String id = "user-123";
        Usuario usuarioMock = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345");
        when(usuarioGateway.findById(id)).thenReturn(usuarioMock);

        // Act
        getByIdUsuarioService.findById(id);

        // Assert
        verify(usuarioGateway, times(1)).findById(id);
        verifyNoMoreInteractions(usuarioGateway);
    }

    @Test
    void findById_ComDiferentesFormatosDeId_DeveComportarCorretamente() {
        // Teste com ID numérico
        String idNumerico = "123";
        Usuario usuario1 = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345");
        when(usuarioGateway.findById(idNumerico)).thenReturn(usuario1);
        assertNotNull(getByIdUsuarioService.findById(idNumerico));

        // Teste com UUID
        String uuid = "550e8400-e29b-41d4-a716-446655440000";
        Usuario usuario2 =Usuario.create(
                "Joao Silva",
                "joao@email.com",
                "loginjoao", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345");
        when(usuarioGateway.findById(uuid)).thenReturn(usuario2);
        assertNotNull(getByIdUsuarioService.findById(uuid));

        // Teste com string complexa
        String stringComplexa = "usr-123-abc@456";
        Usuario usuario3 = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345");
        when(usuarioGateway.findById(stringComplexa)).thenReturn(usuario3);
        assertNotNull(getByIdUsuarioService.findById(stringComplexa));

        verify(usuarioGateway, times(3)).findById(any());
    }

    @Test
    void findById_DeveRetornarUsuarioCompleto() {
        // Arrange
        String id = "user-123";
        Usuario usuarioMock = Usuario.create(
                "Maria Silva",
                "maria@email.com",
                "loginmaria", // login diferente do comando
                true,
                "senha123",
                LocalDate.now(),
                "Rua A, 345");
        usuarioMock.setId(id);
        usuarioMock.setNome("João Silva");
        usuarioMock.setEmail("joao@email.com");

        when(usuarioGateway.findById(id)).thenReturn(usuarioMock);

        // Act
        Usuario resultado = getByIdUsuarioService.findById(id);

        // Assert
        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        assertEquals("joao@email.com", resultado.getEmail());
    }

    @Test
    void findById_ComStringVazia_DeveRetornarNulo() {
        // Arrange
        String idVazio = "";
        when(usuarioGateway.findById(idVazio)).thenReturn(null);

        // Act
        Usuario resultado = getByIdUsuarioService.findById(idVazio);

        // Assert
        assertNull(resultado);
        verify(usuarioGateway).findById(idVazio);
    }
}