package br.com.ourogourmet.usuario.usecase;

import br.com.ourogourmet.usuario.entities.Usuario;

import java.util.List;

public interface GetAllUsuarioUseCase {
    List<Usuario> findAll(int page, int size);
}
