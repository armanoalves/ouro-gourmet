package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.gateway.TipoUsuarioGateway;
import br.com.ourogourmet.domain.usecases.GetAllTipoUsuarioUseCase;
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
