package br.com.ourogourmet.core.usecases;

import br.com.ourogourmet.core.dto.ValidarLoginUsuarioDTO;
import br.com.ourogourmet.core.exceptions.UsuarioSenhaInvalidaException;
import br.com.ourogourmet.core.interfaces.UsuarioRepository;
import br.com.ourogourmet.core.interfaces.ValidarLoginUsuarioUseCase;
import org.springframework.stereotype.Service;

@Service
public class ValidarLoginUsuarioService implements ValidarLoginUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public ValidarLoginUsuarioService(UsuarioRepository usuarioRepository) { this.usuarioRepository = usuarioRepository; }

    @Override
    public void validar(ValidarLoginUsuarioDTO validarLoginUsuarioDTO) {

        var usuario = this.usuarioRepository.findByLogin(validarLoginUsuarioDTO.login());

        if (!usuario.getSenha().equals(validarLoginUsuarioDTO.senha())) {
            throw new UsuarioSenhaInvalidaException();
        }
    }
}
