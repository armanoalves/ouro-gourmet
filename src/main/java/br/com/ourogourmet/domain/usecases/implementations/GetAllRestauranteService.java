package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.usecases.GetAllRestauranteUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllRestauranteService implements GetAllRestauranteUseCase {
    @Override
    public List<Restaurante> execute(int page, int size, RestauranteGateway restauranteGateway) {

        return restauranteGateway.buscarTodos(page,size);
    }
}
