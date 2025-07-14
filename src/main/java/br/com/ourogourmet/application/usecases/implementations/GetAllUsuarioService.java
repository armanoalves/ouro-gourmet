package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.usecases.GetAllUsuarioUseCase;
import br.com.ourogourmet.core.entities.Usuario;
import br.com.ourogourmet.application.interfaces.UsuarioGateway;
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
