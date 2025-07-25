package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.TipoUsuarioGateway;
import br.com.ourogourmet.application.usecases.GetByIdTipoUsuarioUseCase;
import br.com.ourogourmet.core.entities.TipoUsuario;
import org.springframework.stereotype.Service;

@Service
public class GetByIdTipoUsuarioService implements GetByIdTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioRepository;

    public GetByIdTipoUsuarioService (TipoUsuarioGateway tipoUsuarioRepository){
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public TipoUsuario findById(String id) {
        return this.tipoUsuarioRepository.findById(id);
    }
}
