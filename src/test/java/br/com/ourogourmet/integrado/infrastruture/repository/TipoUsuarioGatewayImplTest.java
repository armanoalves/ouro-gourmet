package br.com.ourogourmet.integrado.repository;

import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.entities.enums.TipoUsuarioEnum;
import br.com.ourogourmet.domain.exceptions.TipoUsuarioNaoEncontradoException;
import br.com.ourogourmet.domain.usecases.AlterarTipoUsuarioUseCase;
import br.com.ourogourmet.infrastructure.repository.TipoUsuarioGatewayImpl;
import br.com.ourogourmet.infrastructure.repository.TipoUsuarioJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
@Sql(scripts = "/sql/tipo_usuario.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/clean_tipo_usuario.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class TipoUsuarioGatewayImplTest {

    @Autowired
    private TipoUsuarioGatewayImpl tipoUsuarioGateway;

    @Autowired
    private TipoUsuarioJpaRepository tipoUsuarioJpaRepository;

    @Test
    void deveBuscarTipoUsuarioPorIdComSucesso() {
        String id = "101"; // corresponde ao tipo_usuario.sql

        TipoUsuario tipoUsuario = tipoUsuarioGateway.findById(id);

        assertNotNull(tipoUsuario);
        assertEquals(101L, tipoUsuario.getId());
        assertEquals(TipoUsuarioEnum.DONO, tipoUsuario.getTipo());
    }

    @Test
    void deveLancarExcecao_QuandoNaoEncontrarTipoUsuarioPorId() {
        String id = "999";

        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> {
            tipoUsuarioGateway.findById(id);
        });
    }

    @Test
    void deveBuscarTodosOsTipoUsuario() {
        List<TipoUsuario> lista = tipoUsuarioGateway.findAll(0, 10);

        assertThat(lista)
                .isNotEmpty()
                .hasSize(2); // de acordo com o tipo_usuario.sql
    }

    @Test
    void deveIncluirTipoUsuarioComSucesso() {
        TipoUsuario tipoUsuario = TipoUsuario.create(TipoUsuarioEnum.CLIENTE);

        Long idSalvo = tipoUsuarioGateway.incluir(tipoUsuario);

        assertNotNull(idSalvo);
        assertTrue(tipoUsuarioJpaRepository.existsById(idSalvo));
    }

    @Test
    void deveAlterarTipoUsuarioComSucesso() {
        Long id = 102L;
        AlterarTipoUsuarioUseCase.AlterarTipoUsuarioCommand command =
                new AlterarTipoUsuarioUseCase.AlterarTipoUsuarioCommand(id, TipoUsuarioEnum.CLIENTE);

        tipoUsuarioGateway.alterar(command);

        TipoUsuario alterado = tipoUsuarioGateway.findById(id.toString());

        assertEquals(TipoUsuarioEnum.CLIENTE, alterado.getTipo());
    }

    @Test
    void deveDeletarTipoUsuarioComSucesso() {
        String id = "101";
        tipoUsuarioGateway.delete(id);

        assertFalse(tipoUsuarioJpaRepository.existsById(Long.parseLong(id)));
    }

    @Test
    void deveLancarExcecao_QuandoDeletarTipoUsuarioInexistente() {
        String id = "999";
        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> {
            tipoUsuarioGateway.delete(id);
        });
    }
}