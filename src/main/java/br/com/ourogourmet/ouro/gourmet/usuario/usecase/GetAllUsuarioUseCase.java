package br.com.ourogourmet.ouro.gourmet.usuario.usecase;

import br.com.ourogourmet.ouro.gourmet.usuario.entities.Usuario;

import java.util.List;

public interface GetAllUsuarioUseCase {
    List<Usuario> findAll(int page, int size);
}
