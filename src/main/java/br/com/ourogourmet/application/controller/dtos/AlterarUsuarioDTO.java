package br.com.ourogourmet.application.controller.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlterarUsuarioDTO(

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
        Boolean ativo,

        @NotNull
        Long tipoUsuarioId) {
}