package br.com.ourogourmet.ouro.gourmet.usuario.services;

import br.com.ourogourmet.ouro.gourmet.usuario.entities.Usuario;
import br.com.ourogourmet.ouro.gourmet.usuario.repositories.UsuarioRepository;
import br.com.ourogourmet.ouro.gourmet.usuario.usecase.GetByIdUsuarioUseCase;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetByIdUsuarioService implements GetByIdUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public GetByIdUsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public Optional<Usuario> findById(String id){
        return this.usuarioRepository.findById(id);
    }
}
