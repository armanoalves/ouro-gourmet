package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.domain.exceptions.UsuarioCriacaoFalhaException;
import br.com.ourogourmet.domain.exceptions.UsuarioDuplicadoException;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.CriarUsuarioUseCase.CriarUsuarioCommand;
import br.com.ourogourmet.domain.usecases.implementations.CriarUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarUsuarioServiceTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private CriarUsuarioService criarUsuarioService;

    @Test
    void save_ComUsuarioNovo_DeveSalvarComSucesso() {
        // Arrange
        CriarUsuarioCommand command = new CriarUsuarioCommand(
                "João Silva",
                "Rua A, 123",
                "senha123",
                "joao@email.com",
                "joaosilva",
                true,
                1L);

        when(usuarioGateway.findAll(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(usuarioGateway.save(any(Usuario.class))).thenReturn(1);

        // Act
        criarUsuarioService.save(command);

        // Assert
        verify(usuarioGateway).findAll(Integer.MAX_VALUE, 0);
        verify(usuarioGateway).save(argThat(usuario ->
                usuario.getNome().equals("João Silva") &&
                        usuario.getEmail().equals("joao@email.com")
        ));
    }

    @Test
    void save_ComEmailDuplicado_DeveLancarUsuarioDuplicadoException() {
        // Arrange
        CriarUsuarioCommand command = new CriarUsuarioCommand(
                "João Silva",
                "Rua A, 123",
                "senha123",
                "joao@email.com",
                "joaosilva",
                true,
                1L);

        Usuario existente = Usuario.create("João Silva","joao@email.com","login",true,"senha",LocalDate.now(),"Rua da Glória, 355", 1L);
        existente.setEmail("joao@email.com");

        when(usuarioGateway.findAll(anyInt(), anyInt())).thenReturn(List.of(existente));

        // Act & Assert
        UsuarioDuplicadoException exception = assertThrows(UsuarioDuplicadoException.class, () -> {
            criarUsuarioService.save(command);
        });

        assertEquals("A informação do e-mail já existe no banco", exception.getMessage());
        verify(usuarioGateway).findAll(Integer.MAX_VALUE, 0);
        verify(usuarioGateway, never()).save(any());
    }

    @Test
    void save_ComFalhaNaPersistencia_DeveLancarUsuarioCriacaoFalhaException() {
        // Arrange
        CriarUsuarioCommand command = new CriarUsuarioCommand(
                "João Silva",
                "Rua A, 123",
                "senha123",
                "joao@email.com",
                "joaosilva",
                true,
                1L);

        when(usuarioGateway.findAll(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(usuarioGateway.save(any(Usuario.class))).thenReturn(0);

        // Act & Assert
        UsuarioCriacaoFalhaException exception = assertThrows(UsuarioCriacaoFalhaException.class, () -> {
            criarUsuarioService.save(command);
        });

        assertEquals("Falha ao criar usuário: Erro ao salvar usuário", exception.getMessage());
        verify(usuarioGateway).save(any(Usuario.class));
    }

    @Test
    void save_DeveCriarUsuarioComDadosCorretos() {
        // Arrange
        CriarUsuarioCommand command = new CriarUsuarioCommand(
                "Maria Souza",
                "Rua B, 456",
                "outrasenha",
                "maria@email.com",
                "mariasouza",
                false,
                1L);

        when(usuarioGateway.findAll(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(usuarioGateway.save(any(Usuario.class))).thenReturn(1);

        // Act
        criarUsuarioService.save(command);

        // Assert
        verify(usuarioGateway).save(argThat(usuario ->
                usuario.getNome().equals("Maria Souza") &&
                        usuario.getEndereco().equals("Rua B, 456") &&
                        usuario.getEmail().equals("maria@email.com") &&
                        usuario.getLogin().equals("mariasouza") &&
                        !usuario.getAtivo()
        ));
    }

    @Test
    void save_DeveVerificarCaseInsensitiveParaEmail() {
        // Arrange
        CriarUsuarioCommand command = new CriarUsuarioCommand(
                "João Silva",
                "Rua A, 123",
                "senha123",
                "JOAO@email.com", // email em maiúsculas
                "joaosilva",
                true,
                1L);

        Usuario existente = Usuario.create("João Silva","joao@email.com","login",true,"senha",LocalDate.now(),"Rua da Glória, 355", 1L);
        existente.setEmail("joao@email.com"); // email em minúsculas

        when(usuarioGateway.findAll(anyInt(), anyInt())).thenReturn(List.of(existente));

        // Act & Assert
        assertThrows(UsuarioDuplicadoException.class, () -> {
            criarUsuarioService.save(command);
        });
    }
}