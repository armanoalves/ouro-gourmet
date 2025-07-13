package br.com.ourogourmet.application.interfaces;

import br.com.ourogourmet.core.entities.Usuario;

import java.util.List;

public interface GetAllUsuarioUseCase {
    List<Usuario> findAll(int page, int size);
}
