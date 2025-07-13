package br.com.ourogourmet.core.usecases;

import br.com.ourogourmet.core.dto.CriarUsuarioDTO;
import br.com.ourogourmet.core.entities.Usuario;
import br.com.ourogourmet.core.exceptions.UsuarioCriacaoFalhaException;
import br.com.ourogourmet.core.exceptions.UsuarioDuplicadoException;
import br.com.ourogourmet.core.interfaces.CriarUsuarioUseCase;
import br.com.ourogourmet.core.interfaces.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioService implements CriarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public CriarUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void save(CriarUsuarioDTO usuario) {
        var usuariosExistentes = usuarioRepository.findAll(Integer.MAX_VALUE, 0);

        boolean emailJaExiste = usuariosExistentes.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(usuario.email()));

        if (emailJaExiste) {
            throw new UsuarioDuplicadoException("do e-mail");
        }

        var incluirUsuario = new Usuario(usuario);
        var save = this.usuarioRepository.save(incluirUsuario);

        if (save != 1) {
            throw new UsuarioCriacaoFalhaException("Erro ao salvar usuário");
        }
    }
}
