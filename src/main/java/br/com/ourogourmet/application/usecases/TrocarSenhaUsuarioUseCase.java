package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.core.dto.TrocarSenhaUsuarioDTO;

public interface TrocarSenhaUsuarioUseCase {
    void trocarSenha(TrocarSenhaUsuarioDTO senha, String id);
}
