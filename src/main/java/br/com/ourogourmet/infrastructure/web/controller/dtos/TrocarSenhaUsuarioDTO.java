package br.com.ourogourmet.infrastructure.web.controller.dtos;

import jakarta.validation.constraints.NotBlank;

public record TrocarSenhaUsuarioDTO(
        @NotBlank(message = "O campo senha é obrigatório.")
        String senha)  {
}
