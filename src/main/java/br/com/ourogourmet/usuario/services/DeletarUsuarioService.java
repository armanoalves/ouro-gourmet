package br.com.ourogourmet.usuario.services;

import br.com.ourogourmet.usuario.exceptions.UsuarioNaoDeletadoException;
import br.com.ourogourmet.usuario.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.usuario.repositories.UsuarioRepository;
import br.com.ourogourmet.usuario.usecase.DeletarUsuarioUseCase;
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
