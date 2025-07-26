package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.gateway.TipoUsuarioGateway;
import br.com.ourogourmet.domain.usecases.GetByIdTipoUsuarioUseCase;
import org.springframework.stereotype.Service;

@Service
public class GetByIdTipoUsuarioService implements GetByIdTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioRepository;

    public GetByIdTipoUsuarioService(TipoUsuarioGateway tipoUsuarioRepository){
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public TipoUsuario findById(String id) {
        return this.tipoUsuarioRepository.findById(id);
    }
}
