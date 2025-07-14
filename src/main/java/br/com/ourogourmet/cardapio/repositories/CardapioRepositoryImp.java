package br.com.ourogourmet.cardapio.repositories;

import br.com.ourogourmet.cardapio.entities.Cardapio;
import br.com.ourogourmet.usuario.entities.Usuario;
import br.com.ourogourmet.usuario.exceptions.UsuarioNaoEncontradoException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardapioRepositoryImp implements CardapioRepository {

    private final JdbcClient jdbcClient;

    public CardapioRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Cardapio findById(String id) {
        return this.jdbcClient
                .sql("SELECT * FROM cardapio WHERE id = :id")
                .param("id", id)
                .query(Cardapio.class)
                .optional()
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    @Override
    public List<Cardapio> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT * FROM cardapio LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Cardapio.class)
                .list();
    }

    @Override
    public Integer save(Cardapio cardapio) {
        return this.jdbcClient
                .sql("INSERT into cardapio (id, nome, descricao, preco, consumo_local, foto, data_alteracao, data_criacao) " +
                        "VALUES (:id, :nome,:descricao,:preco, :consumoLocal, :foto, :dataAlteracao, :dataCriacao)")
                .param("id", cardapio.getId())
                .param("nome", cardapio.getNome())
                .param("descricao", cardapio.getDescricao())
                .param("preco", cardapio.getPreco())
                .param("consumoLocal", cardapio.getConsumoLocal())
                .param("foto", cardapio.getFoto())
                .param("dataAlteracao", cardapio.getDataAlteracao())
                .param("dataCriacao", cardapio.getDataCriacao())
                .update();
    }

    @Override
    public Integer update(Cardapio cardapio, String id) {
        return this.jdbcClient.sql("UPDATE cardapio " +
                        "SET nome = :nome, descricao = :descricao, " +
                        "preco = :preco, consumoLocal = :consumoLocal, foto = :foto, data_alteracao = :dataAlteracao " +
                        "WHERE id = :id")
                .param("id", id)
                .param("nome", cardapio.getNome())
                .param("descricao", cardapio.getDescricao())
                .param("preco", cardapio.getPreco())
                .param("consumoLocal", cardapio.getConsumoLocal())
                .param("foto", cardapio.getFoto())
                .param("dataAlteracao", cardapio.getDataAlteracao())
                .update();
    }

    @Override
    public Integer delete(String id) {
        return this.jdbcClient.sql("DELETE FROM cardapio WHERE id = :id")
                .param("id", id)
                .update();
    }

}
