package br.com.ourogourmet.application.interfaces;

import br.com.ourogourmet.core.entities.Restaurante;

import java.util.Optional;

public interface RestauranteGateway {

    void incluir(Restaurante restaurante);
    Restaurante findById(String id);
    Optional<Restaurante> findByNome(String nome);
}
