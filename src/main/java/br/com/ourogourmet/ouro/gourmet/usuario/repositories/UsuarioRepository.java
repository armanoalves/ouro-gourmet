package br.com.ourogourmet.ouro.gourmet.usuario.repositories;

import br.com.ourogourmet.ouro.gourmet.usuario.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findById(String id);
    List<Usuario> findAll(int size, int offset);
    Integer save(Usuario usuario);
    Integer update(Usuario usuario, String id);
    Integer delete(String id);
    Integer atualizarSenha(Usuario usuario, String id);
    Optional<Usuario> findByLogin(String login);

}
