package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.UsuarioGateway;
import br.com.ourogourmet.application.usecases.TrocarSenhaUsuarioUseCase;
import br.com.ourogourmet.core.exceptions.UsuarioNaoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class TrocarSenhaUsuarioService implements TrocarSenhaUsuarioUseCase {
    private final UsuarioGateway usuarioRepository;

    public TrocarSenhaUsuarioService(UsuarioGateway usuarioRepository) { this.usuarioRepository = usuarioRepository; }

    @Override
    public void trocarSenha(TrocarSenhaUsuarioCommand trocarSenhaUsuarioDTO, String id) {

        var usuario = usuarioRepository.findById(id);
        usuario.alterarSenha(trocarSenhaUsuarioDTO.senha());

        var update = usuarioRepository.atualizarSenha(usuario, id);
        if (update == 0) {
            throw new UsuarioNaoEncontradoException();
        }
    }
}
