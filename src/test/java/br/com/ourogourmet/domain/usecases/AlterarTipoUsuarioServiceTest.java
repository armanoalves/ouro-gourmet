package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.enums.TipoUsuarioEnum;
import br.com.ourogourmet.domain.gateway.TipoUsuarioGateway;
import br.com.ourogourmet.domain.usecases.AlterarTipoUsuarioUseCase.AlterarTipoUsuarioCommand;
import br.com.ourogourmet.domain.usecases.implementations.AlterarTipoUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AlterarTipoUsuarioServiceTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private AlterarTipoUsuarioService alterarTipoUsuarioService;

    @Test
    void execute_DeveChamarMetodoAlterarDoGateway() {
        // Arrange
        Long id = 1L;
        String novoTipo = "DONO";

        AlterarTipoUsuarioCommand command = new AlterarTipoUsuarioCommand(id, novoTipo);

        // Act
        alterarTipoUsuarioService.execute(command);

        // Assert
        verify(tipoUsuarioGateway).alterar(eq(command));
    }
}