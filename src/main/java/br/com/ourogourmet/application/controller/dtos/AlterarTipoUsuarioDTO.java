package br.com.ourogourmet.application.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AlterarTipoUsuarioDTO(
        @NotBlank(message = "O campo 'tipo' é obrigatório.")
        @Size(min = 3, max = 50, message = "O tipo deve ter entre 3 e 50 caracteres.")
        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O tipo deve conter apenas letras e espaços.")
        String tipo
){
}
