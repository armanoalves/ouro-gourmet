package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.TipoUsuario;

public interface GetByIdTipoUsuarioUseCase {
    TipoUsuario findById(String id);
}
