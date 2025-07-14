package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.RestauranteGateway;
import br.com.ourogourmet.application.usecases.AlterarRestauranteUseCase;
import br.com.ourogourmet.core.exceptions.RestauranteNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlterarRestauranteService implements AlterarRestauranteUseCase {

    private final RestauranteGateway restauranteRepository;

    @Override
    public void execute(AlterarRestauranteCommand restauranteCommand) {

        restauranteRepository.alterar(restauranteCommand);

    }
}
