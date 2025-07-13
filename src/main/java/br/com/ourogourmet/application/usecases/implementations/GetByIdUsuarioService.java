package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.usecases.GetByIdUsuarioUseCase;
import br.com.ourogourmet.core.entities.Usuario;
import br.com.ourogourmet.application.interfaces.UsuarioGateway;
import org.springframework.stereotype.Service;

@Service
public class GetByIdUsuarioService implements GetByIdUsuarioUseCase {
    private final UsuarioGateway usuarioRepository;

    public GetByIdUsuarioService(UsuarioGateway usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario findById(String id){
        return this.usuarioRepository.findById(id);
    }
}
