package br.com.ourogourmet.infrastructure.web.controller.dtos;

import br.com.ourogourmet.core.entities.enums.TipoUsuarioEnum;
import jakarta.validation.constraints.NotBlank;

public record AlterarTipoUsuarioDTO(

        @NotBlank
        TipoUsuarioEnum tipoUsuario) {
}
