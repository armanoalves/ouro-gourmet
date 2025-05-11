package br.com.ourogourmet.ouro.gourmet.repositories;

import br.com.ourogourmet.ouro.gourmet.entities.Usuario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImp implements UsuarioRepository {

    private final JdbcClient jdbcClient;

    public UsuarioRepositoryImp(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Usuario> findById(String id) {
        return this.jdbcClient
                .sql("SELECT * FROM usuario WHERE id = :id")
                .param("id",id)
                .query(Usuario.class).optional();
    }

    @Override
    public List<Usuario> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT * FROM usuario LIMIT :size OFFSET :offset")
                .param("size",size)
                .param("offset",offset)
                .query(Usuario.class)
                .list();
    }

    @Override
    public Integer save(Usuario usuario) {
        return this.jdbcClient
                .sql("INSERT into usuario (id, email, login, ativo) VALUES (:id , :email, :login, :ativo)")
                .param("id",usuario.getId())
                .param("email",usuario.getEmail())
                .param("login",usuario.getLogin())
                .param("ativo", usuario.getAtivo())
                .update();
    }

    @Override
    public Integer update(Usuario usuario, String id) {
        return this.jdbcClient.sql("UPDATE usuario SET email = :email, login = :login, ativo = :ativo WHERE id = :id")
                .param("id",id)
                .param("email",usuario.getEmail())
                .param("login",usuario.getLogin())
                .param("ativo", usuario.getAtivo())
                .update();
    }

    @Override
    public Integer delete(String id) {
        return this.jdbcClient.sql("DELETE FROM usuario WHERE id = :id")
                .param("id",id)
                .update();
    }
}
