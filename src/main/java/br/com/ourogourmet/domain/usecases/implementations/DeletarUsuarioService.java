package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.usecases.DeletarUsuarioUseCase;
import br.com.ourogourmet.domain.exceptions.UsuarioNaoDeletadoException;
import br.com.ourogourmet.domain.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import org.springframework.stereotype.Service;

@Service
public class DeletarUsuarioService implements DeletarUsuarioUseCase {

    private final UsuarioGateway usuarioRepository;

    public DeletarUsuarioService(UsuarioGateway usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void delete(String id) {
        var delete = this.usuarioRepository.delete(id);

        if (delete == 0) {
            throw new UsuarioNaoEncontradoException();
        }

        if (delete < 0) {
            throw new UsuarioNaoDeletadoException(id);
        }
    }
}
