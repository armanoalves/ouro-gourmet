package br.com.ourogourmet.application.controller.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
        @Column(unique = true)
        String login,

        @NotNull
        Boolean ativo) {
}