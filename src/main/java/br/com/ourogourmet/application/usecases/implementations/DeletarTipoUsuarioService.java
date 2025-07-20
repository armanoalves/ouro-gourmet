package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.TipoUsuarioGateway;
import br.com.ourogourmet.application.usecases.DeletarTipoUsuarioUseCase;
import br.com.ourogourmet.core.exceptions.TipoUsuarioNaoDeletadoException;
import br.com.ourogourmet.core.exceptions.TipoUsuarioNaoEncontradoException;
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
