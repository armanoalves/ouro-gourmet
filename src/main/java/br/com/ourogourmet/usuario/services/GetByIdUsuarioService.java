package br.com.ourogourmet.usuario.services;

import br.com.ourogourmet.usuario.entities.Usuario;
import br.com.ourogourmet.usuario.repositories.UsuarioRepository;
import br.com.ourogourmet.usuario.usecase.GetByIdUsuarioUseCase;
import org.springframework.stereotype.Service;

@Service
public class GetByIdUsuarioService implements GetByIdUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public GetByIdUsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario findById(String id){
        return this.usuarioRepository.findById(id);
    }
}
