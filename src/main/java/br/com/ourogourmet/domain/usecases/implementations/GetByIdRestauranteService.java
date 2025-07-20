package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.exceptions.RestauranteNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.usecases.GetByIdRestauranteUseCase;
import org.springframework.stereotype.Service;

@Service
public class GetByIdRestauranteService implements GetByIdRestauranteUseCase {

    @Override
    public Restaurante execute(Long id, RestauranteGateway restauranteGateway) {

        var restauranteOptional =  restauranteGateway.buscarPorId(id).orElseThrow(() -> new RestauranteNaoEncontradoException(id.toString()));;

        return restauranteOptional;
    }
}
