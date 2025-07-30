package br.com.ourogourmet.application.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CriarTipoUsuarioDTO(
    @NotBlank(message = "O campo 'tipoUsuario' é obrigatório.")
    @Size(min = 3, max = 50, message = "O tipo de usuário deve ter entre 3 e 50 caracteres.")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O tipo de usuário deve conter apenas letras e espaços.")
    String tipoUsuario
) {
        }
