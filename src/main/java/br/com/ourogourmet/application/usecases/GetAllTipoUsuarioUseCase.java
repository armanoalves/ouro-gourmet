package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.core.entities.TipoUsuario;

import java.util.List;

public interface GetAllTipoUsuarioUseCase {
    List<TipoUsuario> findAll(int page, int size);
}
