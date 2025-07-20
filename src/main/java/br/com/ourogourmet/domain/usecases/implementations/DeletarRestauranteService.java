package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.usecases.DeletarRestauranteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletarRestauranteService implements DeletarRestauranteUseCase {

    @Override
    public void execute(Long id, RestauranteGateway restauranteGateway) {

        var optionalRestaurante = restauranteGateway.buscarPorId(id);

        optionalRestaurante.ifPresent(restauranteGateway::deletar);

    }
}
