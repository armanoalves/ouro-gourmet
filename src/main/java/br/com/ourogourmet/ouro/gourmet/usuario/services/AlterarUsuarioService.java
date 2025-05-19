package br.com.ourogourmet.ouro.gourmet.usuario.services;

import br.com.ourogourmet.ouro.gourmet.usuario.entities.Usuario;
import br.com.ourogourmet.ouro.gourmet.usuario.exceptions.UsuarioNaoExisteException;
import br.com.ourogourmet.ouro.gourmet.usuario.repositories.UsuarioRepository;
import br.com.ourogourmet.ouro.gourmet.usuario.usecase.AlterarUsuarioUseCase;
import org.springframework.stereotype.Service;

@Service
public class AlterarUsuarioService implements AlterarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public AlterarUsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public void update(AlterarUsuarioDTO usuario, String id){

        var alterarUsuario = new Usuario(usuario);
        var update = this.usuarioRepository.update(alterarUsuario,id);

        if (update == 0){
            throw new UsuarioNaoExisteException(id);
        }
    }

}
