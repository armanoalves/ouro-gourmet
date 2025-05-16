package br.com.ourogourmet.ouro.gourmet.usuario.services;

import br.com.ourogourmet.ouro.gourmet.usuario.exceptions.UsuarioCamposInconsistentesException;
import br.com.ourogourmet.ouro.gourmet.usuario.exceptions.UsuarioNaoExisteException;
import br.com.ourogourmet.ouro.gourmet.usuario.repositories.UsuarioRepository;
import br.com.ourogourmet.ouro.gourmet.usuario.usecase.TrocarSenhaUsuarioUseCase;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class TrocarSenhaUsuarioService implements TrocarSenhaUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public TrocarSenhaUsuarioService(UsuarioRepository usuarioRepository) { this.usuarioRepository = usuarioRepository; }

    @Override
    public void trocarSenha(TrocarSenhaUsuarioDTO trocarSenhaUsuarioDTO, String id) {

        var usuarioOptional = usuarioRepository.findById(id);

        if(usuarioOptional.isEmpty()) {
            throw new UsuarioNaoExisteException(id);
        }

        var usuario = usuarioOptional.get();
        usuario.alterarSenha(trocarSenhaUsuarioDTO.senha());

        var update = usuarioRepository.atualizarSenha(usuario, id);

        if (update == 0) {
            throw new UsuarioNaoExisteException(id);
        }


    }
}
