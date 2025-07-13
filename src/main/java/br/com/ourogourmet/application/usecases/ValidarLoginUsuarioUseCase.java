package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.infrastructure.web.controller.dtos.ValidarLoginUsuarioDTO;

public interface ValidarLoginUsuarioUseCase {

    void validar(ValidarLoginUsuarioDTO dto);

}
