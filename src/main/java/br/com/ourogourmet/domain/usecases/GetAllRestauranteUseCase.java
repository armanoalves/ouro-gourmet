package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;

import java.util.List;

public interface GetAllRestauranteUseCase {
    List<Restaurante> execute(int page, int size, RestauranteGateway restauranteGateway);
}
