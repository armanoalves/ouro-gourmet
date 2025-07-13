package br.com.ourogourmet.core.interfaces;

import br.com.ourogourmet.core.entities.Usuario;

import java.util.List;

public interface GetAllUsuarioUseCase {
    List<Usuario> findAll(int page, int size);
}
