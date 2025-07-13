package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.infrastructure.web.controller.dtos.TrocarSenhaUsuarioDTO;

public interface TrocarSenhaUsuarioUseCase {
    void trocarSenha(TrocarSenhaUsuarioDTO senha, String id);
}
