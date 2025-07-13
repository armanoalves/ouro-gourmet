package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.UsuarioGateway;
import br.com.ourogourmet.application.usecases.AlterarUsuarioUseCase;
import br.com.ourogourmet.core.entities.Usuario;
import br.com.ourogourmet.core.exceptions.UsuarioDuplicadoException;
import br.com.ourogourmet.core.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.core.exceptions.UsuarioValidacaoException;
import org.springframework.stereotype.Service;

@Service
public class AlterarUsuarioService implements AlterarUsuarioUseCase {

    private final UsuarioGateway usuarioRepository;

    public AlterarUsuarioService(UsuarioGateway usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void update(AlterarUsuarioCommand usuarioDto, String id) {
        var usuarioExistente = usuarioRepository.findById(id);

        validarAlteracoes(usuarioExistente, usuarioDto);

        Usuario usuarioAlterado = Usuario.alterar(usuarioDto);
        var update = this.usuarioRepository.update(usuarioAlterado, id);

        if (update == 0) {
            throw new UsuarioNaoEncontradoException();
        }
    }

    private void validarAlteracoes(Usuario usuarioAtual, AlterarUsuarioCommand novoUsuario) {
        if (!usuarioAtual.getLogin().equals(novoUsuario.login())) {
            throw new UsuarioDuplicadoException("do login");
        }

        boolean emailJaExiste = usuarioRepository.existsByEmail(novoUsuario.email());
        if (emailJaExiste) {
            throw new UsuarioDuplicadoException("do e-mail");
        }

        if (!novoUsuario.ativo()) {
            throw new UsuarioValidacaoException("O campo 'ativo' não pode ser falso.");
        }
    }

}
