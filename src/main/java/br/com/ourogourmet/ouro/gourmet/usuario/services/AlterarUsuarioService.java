package br.com.ourogourmet.ouro.gourmet.usuario.services;

import br.com.ourogourmet.ouro.gourmet.usuario.entities.Usuario;
import br.com.ourogourmet.ouro.gourmet.usuario.exceptions.UsuarioAlteracaoInvalidaException;
import br.com.ourogourmet.ouro.gourmet.usuario.exceptions.UsuarioNaoExisteException;
import br.com.ourogourmet.ouro.gourmet.usuario.repositories.UsuarioRepository;
import br.com.ourogourmet.ouro.gourmet.usuario.usecase.AlterarUsuarioUseCase;
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

        var usuarioAlterado = new Usuario(usuarioDto);
        var update = this.usuarioRepository.update(usuarioAlterado, id);

        if (update == 0) {
            throw new UsuarioNaoExisteException(id);
        }
    }

    private void validarAlteracoes(Usuario usuarioAtual, AlterarUsuarioDTO novoUsuario) {
        if (!usuarioAtual.getLogin().equals(novoUsuario.login())) {
            throw new UsuarioAlteracaoInvalidaException("Não é permitido alterar o login.");
        }

        var emailJaExiste = usuarioRepository.existsByEmail(novoUsuario.email());
        if (emailJaExiste) {
            throw new UsuarioAlteracaoInvalidaException("O e-mail informado já está em uso.");
        }

        if (!novoUsuario.ativo()) {
            throw new UsuarioAlteracaoInvalidaException("O campo 'ativo' não pode ser falso.");
        }
    }

}
