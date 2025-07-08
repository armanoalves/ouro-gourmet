package br.com.ourogourmet.usuario.repositories;

import br.com.ourogourmet.usuario.entities.Usuario;

import java.util.List;

public interface UsuarioRepository {

    Usuario findById(String id);
    List<Usuario> findAll(int size, int offset);
    Integer save(Usuario usuario);
    Integer update(Usuario usuario, String id);
    Integer delete(String id);
    Integer atualizarSenha(Usuario usuario, String id);
    Usuario findByLogin(String login);
    boolean existsByEmail(String email);
}
