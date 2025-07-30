package br.com.ourogourmet.application.controller.dtos;

import br.com.ourogourmet.domain.entities.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CriarUsuarioDTO(
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

        @NotNull(message = "O campo ativo é obrigatório.")
        Boolean ativo,

        @NotNull(message = "O campo tipoUsuarioId é obrigatório.")
        TipoUsuario tipoUsuarioId
) {
}