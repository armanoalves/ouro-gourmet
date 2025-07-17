package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.CriarUsuarioUseCase;
import br.com.ourogourmet.domain.entities.Usuario;
import br.com.ourogourmet.domain.exceptions.UsuarioCriacaoFalhaException;
import br.com.ourogourmet.domain.exceptions.UsuarioDuplicadoException;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioService implements CriarUsuarioUseCase {

    private final UsuarioGateway usuarioRepository;

    public CriarUsuarioService(UsuarioGateway usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void save(CriarUsuarioCommand usuario) {
        var usuariosExistentes = usuarioRepository.findAll(Integer.MAX_VALUE, 0);

        boolean emailJaExiste = usuariosExistentes.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(usuario.email()));

        if (emailJaExiste) {
            throw new UsuarioDuplicadoException("do e-mail");
        }

        var incluirUsuario = Usuario.incluir(usuario);
        var save = this.usuarioRepository.save(incluirUsuario);

        if (save != 1) {
            throw new UsuarioCriacaoFalhaException("Erro ao salvar usuário");
        }
    }
}
