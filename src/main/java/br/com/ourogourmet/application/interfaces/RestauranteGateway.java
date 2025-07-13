package br.com.ourogourmet.application.interfaces;

import br.com.ourogourmet.core.entities.Restaurante;

import java.util.Optional;

public interface RestauranteGateway {

    Long incluir(Restaurante restaurante);
    Restaurante findById(Long id);
    Optional<Restaurante> findByNome(String nome);
}
