package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.infrastructure.web.controller.dtos.CriarUsuarioDTO;

public interface CriarUsuarioUseCase {
        void save(CriarUsuarioDTO usuario);
}
