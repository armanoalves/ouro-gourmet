package br.com.ourogourmet.usuario.repositories;

import br.com.ourogourmet.usuario.entities.Usuario;
import br.com.ourogourmet.usuario.exceptions.UsuarioNaoEncontradoException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepositoryImp implements UsuarioRepository {

    private final JdbcClient jdbcClient;

    public UsuarioRepositoryImp(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Usuario findById(String id) {
        return this.jdbcClient
                .sql("SELECT * FROM usuario WHERE id = :id")
                .param("id",id)
                .query(Usuario.class)
                .optional()
                .orElseThrow(UsuarioNaoEncontradoException::new);
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
                .sql("INSERT into usuario (id, nome, endereco, senha, email, login, ativo) VALUES (:id, :nome,:endereco,:senha, :email, :login, :ativo)")
                .param("id",usuario.getId())
                .param("email",usuario.getEmail())
                .param("login",usuario.getLogin())
                .param("ativo", usuario.getAtivo())
                .param("nome",usuario.getNome())
                .param("endereco",usuario.getEndereco())
                .param("senha",usuario.getSenha())
                .update();
    }

    @Override
    public Integer update(Usuario usuario, String id) {
        return this.jdbcClient.sql("UPDATE usuario " +
                        "SET nome = :nome, endereco = :endereco, " +
                        "email = :email, login = :login, ativo = :ativo, data_alteracao = :dataAlteracao " +
                        "WHERE id = :id")
                .param("id",id)
                .param("nome", usuario.getNome())
                .param("endereco",usuario.getEndereco())
                .param("email",usuario.getEmail())
                .param("login",usuario.getLogin())
                .param("ativo", usuario.getAtivo())
                .param("dataAlteracao",usuario.getDataAlteracao())
                .update();
    }

    @Override
    public Integer delete(String id) {
        return this.jdbcClient.sql("DELETE FROM usuario WHERE id = :id")
                .param("id",id)
                .update();
    }

    @Override
    public Integer atualizarSenha(Usuario usuario, String id) {
        return this.jdbcClient.sql("UPDATE usuario SET senha = :senha, data_alteracao = :dataAlteracao WHERE id = :id")
                .param("senha", usuario.getSenha())
                .param("dataAlteracao", usuario.getDataAlteracao())
                .param("id", id)
                .update();
    }

    @Override
    public Usuario findByLogin(String login) {
        return this.jdbcClient
                .sql("SELECT * FROM usuario WHERE login = :login")
                .param("login", login)
                .query(Usuario.class)
                .optional()
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.jdbcClient
                .sql("SELECT EXISTS (SELECT 1 FROM usuario WHERE email = :email)")
                .param("email", email)
                .query(Boolean.class)
                .single();
    }


}
