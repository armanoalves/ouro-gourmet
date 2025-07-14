package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.core.entities.Usuario;

public interface GetByIdUsuarioUseCase {
    Usuario findById(String id);
}
