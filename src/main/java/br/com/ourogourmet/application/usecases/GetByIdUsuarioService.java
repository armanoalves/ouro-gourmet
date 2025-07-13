package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.core.entities.Usuario;
import br.com.ourogourmet.application.interfaces.GetByIdUsuarioUseCase;
import br.com.ourogourmet.application.interfaces.UsuarioRepository;
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
