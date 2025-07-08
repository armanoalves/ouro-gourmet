package br.com.ourogourmet.usuario.usecase;

import br.com.ourogourmet.usuario.entities.Usuario;

public interface GetByIdUsuarioUseCase {
    Usuario findById(String id);
}
