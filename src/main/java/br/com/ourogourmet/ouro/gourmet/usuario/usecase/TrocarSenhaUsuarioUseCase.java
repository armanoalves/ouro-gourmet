package br.com.ourogourmet.ouro.gourmet.usuario.usecase;

import jakarta.validation.constraints.NotBlank;

public interface TrocarSenhaUsuarioUseCase {

    void trocarSenha(TrocarSenhaUsuarioDTO senha, String id);

    record TrocarSenhaUsuarioDTO(
            @NotBlank(message = "O campo senha é obrigatório.")
            String senha)
    {}
}
