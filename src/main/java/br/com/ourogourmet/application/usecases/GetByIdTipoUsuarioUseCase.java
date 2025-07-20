package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.core.entities.TipoUsuario;

public interface GetByIdTipoUsuarioUseCase {
    TipoUsuario findById(String id);
}
