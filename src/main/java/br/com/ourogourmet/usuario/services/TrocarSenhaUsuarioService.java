package br.com.ourogourmet.usuario.services;

import br.com.ourogourmet.usuario.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.usuario.repositories.UsuarioRepository;
import br.com.ourogourmet.usuario.usecase.TrocarSenhaUsuarioUseCase;
import org.springframework.stereotype.Service;

@Service
public class TrocarSenhaUsuarioService implements TrocarSenhaUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public TrocarSenhaUsuarioService(UsuarioRepository usuarioRepository) { this.usuarioRepository = usuarioRepository; }

    @Override
    public void trocarSenha(TrocarSenhaUsuarioDTO trocarSenhaUsuarioDTO, String id) {

        var usuario = usuarioRepository.findById(id);
        usuario.alterarSenha(trocarSenhaUsuarioDTO.senha());

        var update = usuarioRepository.atualizarSenha(usuario, id);
        if (update == 0) {
            throw new UsuarioNaoEncontradoException();
        }
    }
}
