package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.usecases.GetAllUsuarioUseCase;
import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsuarioService implements GetAllUsuarioUseCase {
    private final UsuarioGateway usuarioRepository;

    public GetAllUsuarioService(UsuarioGateway usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAll(int page, int size){
        int offset = (page-1) * size;

        return this.usuarioRepository.findAll(size,offset);
    }
}
