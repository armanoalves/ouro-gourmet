package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.TipoUsuarioGateway;
import br.com.ourogourmet.application.usecases.GetAllTipoUsuarioUseCase;
import br.com.ourogourmet.core.entities.TipoUsuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTipoUsuarioService implements GetAllTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioRepository;

    public GetAllTipoUsuarioService(TipoUsuarioGateway tipoUsuarioRepository){
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public List<TipoUsuario> findAll(int page, int size) {
        int offset = (page-1) * size;

        return this.tipoUsuarioRepository.findAll(size, offset);
    }
}
