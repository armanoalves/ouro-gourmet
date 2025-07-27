package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.entities.enums.TipoUsuarioEnum;
import br.com.ourogourmet.domain.gateway.TipoUsuarioGateway;
import br.com.ourogourmet.domain.usecases.implementations.CriarTipoUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarTipoUsuarioServiceTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private CriarTipoUsuarioService criarTipoUsuarioService;

    @Test
    void execute_DeveCriarTipoUsuarioERetornarId() {
        // Arrange
        var tipoEnum = TipoUsuarioEnum.DONO;
        var command = new CriarTipoUsuarioUseCase.CriarTipoUsuarioCommand(tipoEnum);

        when(tipoUsuarioGateway.incluir(any())).thenReturn(1L);

        // Act
        Long id = criarTipoUsuarioService.execute(command);

        // Assert
        assertEquals(1L, id);

        // Captura o TipoUsuario passado para o gateway
        ArgumentCaptor<TipoUsuario> captor = ArgumentCaptor.forClass(TipoUsuario.class);
        verify(tipoUsuarioGateway).incluir(captor.capture());

        TipoUsuario tipoUsuarioCriado = captor.getValue();
        assertNotNull(tipoUsuarioCriado);
        assertEquals(tipoEnum, tipoUsuarioCriado.getTipo());
    }
}