package br.com.ourogourmet.ouro.gourmet.usuario.usecase;

import jakarta.validation.constraints.NotBlank;

public interface ValidarLoginUsuarioUseCase {

    void validar(ValidarLoginUsuarioDTO dto);

    record ValidarLoginUsuarioDTO(

            @NotBlank(message = "O campo login é obrigatório.")
            String login,

            @NotBlank(message = "O campo senha é obrigatório.")
            String senha)
    {}

}
