package br.com.ourogourmet.core.interfaces;

import br.com.ourogourmet.core.entities.Usuario;

public interface GetByIdUsuarioUseCase {
    Usuario findById(String id);
}
