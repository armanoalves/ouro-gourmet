package br.com.ourogourmet.core.usecases;

import br.com.ourogourmet.core.entities.Usuario;
import br.com.ourogourmet.core.interfaces.GetAllUsuarioUseCase;
import br.com.ourogourmet.core.interfaces.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsuarioService implements GetAllUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public GetAllUsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAll(int page, int size){
        int offset = (page-1) * size;

        return this.usuarioRepository.findAll(size,offset);
    }
}
