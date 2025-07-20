package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.AlterarUsuarioUseCase;
import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.domain.exceptions.UsuarioDuplicadoException;
import br.com.ourogourmet.domain.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.domain.exceptions.UsuarioValidacaoException;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class AlterarUsuarioService implements AlterarUsuarioUseCase {

    private final UsuarioGateway usuarioRepository;

    public AlterarUsuarioService(UsuarioGateway usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void update(AlterarUsuarioCommand cmd, String id) {
        var usuarioExistente = usuarioRepository.findById(id);

        validarAlteracoes(usuarioExistente, cmd);

        Usuario usuarioAlterado = Usuario.alterar(cmd);
        var update = this.usuarioRepository.update(usuarioAlterado, id);

        if (update == 0) {
            throw new UsuarioNaoEncontradoException();
        }
    }

    private void validarAlteracoes(Usuario usuarioAtual, AlterarUsuarioCommand novoUsuario) {
        if (nonNull(usuarioAtual) && !usuarioAtual.getLogin().equals(novoUsuario.login())) {
            throw new UsuarioDuplicadoException("do login");
        }

        boolean emailJaExiste = usuarioRepository.existsByEmail(novoUsuario.email());
        if (emailJaExiste) {
            throw new UsuarioDuplicadoException("do e-mail");
        }

        if (Boolean.FALSE.equals(novoUsuario.ativo())) {
            throw new UsuarioValidacaoException("O campo 'ativo' não pode ser falso.");
        }
    }

}
