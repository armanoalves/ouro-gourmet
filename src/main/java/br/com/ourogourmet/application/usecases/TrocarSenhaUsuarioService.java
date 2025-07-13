package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.core.dto.TrocarSenhaUsuarioDTO;
import br.com.ourogourmet.core.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.application.interfaces.TrocarSenhaUsuarioUseCase;
import br.com.ourogourmet.application.interfaces.UsuarioRepository;
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
