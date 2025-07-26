package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.exceptions.TipoUsuarioNaoDeletadoException;
import br.com.ourogourmet.domain.exceptions.TipoUsuarioNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.TipoUsuarioGateway;
import br.com.ourogourmet.domain.usecases.DeletarTipoUsuarioUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeletarTipoUsuarioService implements DeletarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioRepository;

    public DeletarTipoUsuarioService(TipoUsuarioGateway tipoUsuarioRepository){
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }
    @Override
    public void delete(String id) {

        var delete = this.tipoUsuarioRepository.delete(id);

        if (delete == 0){
            throw new TipoUsuarioNaoEncontradoException();
        }

        if(delete < 0){
            throw new TipoUsuarioNaoDeletadoException(id);
        }
    }
}
