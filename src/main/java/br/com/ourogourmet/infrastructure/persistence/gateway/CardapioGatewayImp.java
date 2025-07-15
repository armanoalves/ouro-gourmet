package br.com.ourogourmet.infrastructure.persistence.gateway;

import br.com.ourogourmet.application.interfaces.CardapioGateway;
import br.com.ourogourmet.core.entities.Cardapio;
import br.com.ourogourmet.core.exceptions.CardapioNaoEncontradoException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardapioGatewayImp implements CardapioGateway {

    private final JdbcClient jdbcClient;

    public CardapioGatewayImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Cardapio findById(String id) {
        return this.jdbcClient
                .sql("SELECT * FROM cardapio WHERE id = :id")
                .param("id", id)
                .query(Cardapio.class)
                .optional()
                .orElseThrow(CardapioNaoEncontradoException::new);
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
                .sql("INSERT into cardapio (id, nome, descricao, preco, consumo_local, foto) " +
                        "VALUES (:id, :nome,:descricao,:preco, :consumoLocal, :foto)")
                .param("id", cardapio.getId())
                .param("nome", cardapio.getNome())
                .param("descricao", cardapio.getDescricao())
                .param("preco", cardapio.getPreco())
                .param("consumoLocal", cardapio.getConsumoLocal())
                .param("foto", cardapio.getFoto())
                .update();
    }

    @Override
    public Integer update(Cardapio cardapio, String id) {
        return this.jdbcClient.sql("UPDATE cardapio " +
                        "SET nome = :nome, descricao = :descricao, " +
                        "preco = :preco, consumoLocal = :consumoLocal, foto = :foto" +
                        "WHERE id = :id")
                .param("id", id)
                .param("nome", cardapio.getNome())
                .param("descricao", cardapio.getDescricao())
                .param("preco", cardapio.getPreco())
                .param("consumoLocal", cardapio.getConsumoLocal())
                .param("foto", cardapio.getFoto())
                .update();
    }

    @Override
    public Integer delete(String id) {
        return this.jdbcClient.sql("DELETE FROM cardapio WHERE id = :id")
                .param("id", id)
                .update();
    }

}
