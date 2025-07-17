package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Usuario;

import java.util.List;

public interface GetAllUsuarioUseCase {
    List<Usuario> findAll(int page, int size);
}
