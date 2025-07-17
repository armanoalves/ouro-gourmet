package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.ValidarLoginUsuarioUseCase;
import br.com.ourogourmet.domain.exceptions.UsuarioSenhaInvalidaException;
import org.springframework.stereotype.Service;

@Service
public class ValidarLoginUsuarioService implements ValidarLoginUsuarioUseCase {
    private final UsuarioGateway usuarioRepository;

    public ValidarLoginUsuarioService(UsuarioGateway usuarioRepository) { this.usuarioRepository = usuarioRepository; }

    @Override
    public void validar(ValidarLoginUsuarioCommand validarLoginUsuarioDTO) {

        var usuario = this.usuarioRepository.findByLogin(validarLoginUsuarioDTO.login());

        if (!usuario.getSenha().equals(validarLoginUsuarioDTO.senha())) {
            throw new UsuarioSenhaInvalidaException();
        }
    }
}
