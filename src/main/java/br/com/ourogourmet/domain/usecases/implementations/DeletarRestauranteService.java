package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.exceptions.RestauranteNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.usecases.DeletarRestauranteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletarRestauranteService implements DeletarRestauranteUseCase {

    @Override
    public void execute(Long id, RestauranteGateway restauranteRepository) {

        var restaurante = restauranteRepository.buscarPorId(id)
                .orElseThrow(()-> new RestauranteNaoEncontradoException(id.toString()));

        restauranteRepository.deletar(restaurante);
    }
}
