package br.com.ourogourmet.core.dto;

import jakarta.validation.constraints.NotBlank;

public record ValidarLoginUsuarioDTO(

        @NotBlank(message = "O campo login é obrigatório.")
        String login,

        @NotBlank(message = "O campo senha é obrigatório.")
        String senha)
{}