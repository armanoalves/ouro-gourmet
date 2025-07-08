package br.com.ourogourmet.usuario.services;

import br.com.ourogourmet.usuario.exceptions.UsuarioSenhaInvalidaException;
import br.com.ourogourmet.usuario.repositories.UsuarioRepository;
import br.com.ourogourmet.usuario.usecase.ValidarLoginUsuarioUseCase;
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
