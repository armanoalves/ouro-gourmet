package br.com.ourogourmet.application.usecases;

import jakarta.validation.constraints.NotBlank;

public interface TrocarSenhaUsuarioUseCase {
    void trocarSenha(TrocarSenhaUsuarioCommand senha, String id);

    record TrocarSenhaUsuarioCommand(
            @NotBlank(message = "O campo senha é obrigatório.")
            String senha) {

    }
}
