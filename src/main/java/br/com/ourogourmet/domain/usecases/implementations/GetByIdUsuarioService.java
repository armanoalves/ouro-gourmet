package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.usecases.GetByIdUsuarioUseCase;
import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
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
