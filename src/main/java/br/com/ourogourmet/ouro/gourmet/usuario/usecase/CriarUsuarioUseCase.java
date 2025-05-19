package br.com.ourogourmet.ouro.gourmet.usuario.usecase;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface CriarUsuarioUseCase {

        void save(CriarUsuarioDTO usuario);

        record CriarUsuarioDTO(
                @NotBlank(message = "O campo nome é obrigatório.")
                String nome,

                @NotBlank(message = "O campo endereco é obrigatório.")
                String endereco,

                @NotBlank(message = "O campo senha é obrigatório.")
                String senha,

                @Email(message = "O e-mail informado é inválido!")
                @NotBlank(message = "O campo e-mail é obrigatório.")
                String email,

                @NotBlank(message = "O campo login é obrigatório.")
                String login,

                @NotNull
                Boolean ativo) {
        }

}
