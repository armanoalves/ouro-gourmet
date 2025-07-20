package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.gateway.RestauranteGateway;

public interface AlterarRestauranteUsuarioUseCase {

    void execute(AlterarRestauranteUsuarioCommand restauranteCommand, RestauranteGateway restauranteRepositor);

    record AlterarRestauranteUsuarioCommand(
            Long id,
            String usuarioId){

    }
}
