package br.com.ourogourmet.application.controller.dtos;

import jakarta.validation.constraints.NotBlank;

public record AlterarTipoUsuarioDTO(

        @NotBlank
        br.com.ourogourmet.domain.entities.enums.TipoUsuarioEnum tipoUsuario) {
}
