package br.com.ourogourmet.core.interfaces;

import br.com.ourogourmet.core.dto.TrocarSenhaUsuarioDTO;

public interface TrocarSenhaUsuarioUseCase {
    void trocarSenha(TrocarSenhaUsuarioDTO senha, String id);
}
