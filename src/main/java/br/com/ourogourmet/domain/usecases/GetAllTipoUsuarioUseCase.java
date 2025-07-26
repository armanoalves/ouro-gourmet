package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.TipoUsuario;

import java.util.List;

public interface GetAllTipoUsuarioUseCase {
    List<TipoUsuario> findAll(int page, int size);
}
