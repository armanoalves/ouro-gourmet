package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.core.exceptions.UsuarioNaoDeletadoException;
import br.com.ourogourmet.core.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.application.interfaces.DeletarUsuarioUseCase;
import br.com.ourogourmet.application.interfaces.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarUsuarioService implements DeletarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public DeletarUsuarioService(UsuarioRepository usuarioRepository) {
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
