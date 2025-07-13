package br.com.ourogourmet.infrastructure.gateway;

import br.com.ourogourmet.application.interfaces.RestauranteGateway;
import br.com.ourogourmet.core.entities.Restaurante;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RestauranteGatewayImpl implements RestauranteGateway {

    private final JdbcClient jdbcClient;

    public RestauranteGatewayImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void incluir(Restaurante restaurante) {

    }

    @Override
    public Restaurante findById(String id) {
        return null;
    }

    @Override
    public Optional<Restaurante> findByNome(String nome) {
        return Optional.empty();
    }
}
