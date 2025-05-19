package br.com.ourogourmet.ouro.gourmet.usuario.usecase;

import br.com.ourogourmet.ouro.gourmet.usuario.entities.Usuario;

import java.util.Optional;

public interface GetByIdUsuarioUseCase {
    Optional<Usuario> findById(String id);
}
