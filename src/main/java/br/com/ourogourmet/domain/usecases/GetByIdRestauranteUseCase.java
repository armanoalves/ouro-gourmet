package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;

public interface GetByIdRestauranteUseCase {

    Restaurante execute(Long id, RestauranteGateway restauranteGateway);
}
