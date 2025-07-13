package br.com.ourogourmet.core.dto;

import jakarta.validation.constraints.NotBlank;

public record TrocarSenhaUsuarioDTO(
        @NotBlank(message = "O campo senha é obrigatório.")
        String senha)  {
}
