package br.com.ourogourmet.ouro.gourmet.usuario.usecase;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface AlterarUsuarioUseCase{
    void update(AlterarUsuarioDTO usuario, String id);

    record AlterarUsuarioDTO(

            @NotBlank(message = "O campo nome é obrigatório.")
            String nome,

            @NotBlank(message = "O campo endereco é obrigatório.")
            String endereco,

            @Email(message = "O e-mail informado é inválido!")
            @NotBlank(message = "O campo e-mail é obrigatório.")
            String email,

            @NotBlank(message = "O campo login é obrigatório.")
            String login,

            @NotNull
            Boolean ativo) {
    }
}


