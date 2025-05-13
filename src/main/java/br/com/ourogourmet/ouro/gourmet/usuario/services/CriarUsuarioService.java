package br.com.ourogourmet.ouro.gourmet.usuario.services;

import br.com.ourogourmet.ouro.gourmet.usuario.entities.Usuario;
import br.com.ourogourmet.ouro.gourmet.usuario.repositories.UsuarioRepository;
import br.com.ourogourmet.ouro.gourmet.usuario.usecase.CriarUsuarioUseCase;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CriarUsuarioService implements CriarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public CriarUsuarioService( UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public void save(CriarUsuarioDTO usuario){

        var incluirUsuario = new Usuario(usuario);
        var save = this.usuarioRepository.save(incluirUsuario);

        Assert.state(save == 1, "Erro ao salvar usuario: " + usuario.email());
    }
}
