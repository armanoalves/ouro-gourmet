package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.gateway.RestauranteGateway;

public interface DeletarRestauranteUseCase {

    void execute(Long id, RestauranteGateway restauranteGateway);
}
