package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Usuario;

public interface GetByIdUsuarioUseCase {
    Usuario findById(String id);
}
