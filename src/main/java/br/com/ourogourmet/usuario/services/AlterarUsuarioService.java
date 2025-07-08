package br.com.ourogourmet.usuario.services;

import br.com.ourogourmet.usuario.entities.Usuario;
import br.com.ourogourmet.usuario.exceptions.UsuarioValidacaoException;
import br.com.ourogourmet.usuario.exceptions.UsuarioDuplicadoException;
import br.com.ourogourmet.usuario.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.usuario.repositories.UsuarioRepository;
import br.com.ourogourmet.usuario.usecase.AlterarUsuarioUseCase;
import org.springframework.stereotype.Service;

@Service
public class AlterarUsuarioService implements AlterarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public AlterarUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void update(AlterarUsuarioDTO usuarioDto, String id) {
        var usuarioExistente = usuarioRepository.findById(id);

        validarAlteracoes(usuarioExistente, usuarioDto);

        Usuario usuarioAlterado = new Usuario(usuarioDto);
        var update = this.usuarioRepository.update(usuarioAlterado, id);

        if (update == 0) {
            throw new UsuarioNaoEncontradoException();
        }
    }

    private void validarAlteracoes(Usuario usuarioAtual, AlterarUsuarioDTO novoUsuario) {
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
