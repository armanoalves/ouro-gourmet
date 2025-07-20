package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.exceptions.RestauranteDuplicadoException;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.usecases.CriarRestauranteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CriarRestauranteService implements CriarRestauranteUseCase {


    @Override
    public Long execute(CriarRestauranteCommand restauranteCommand, RestauranteGateway restauranteRepository) {

        restauranteRepository.buscarPorNome(restauranteCommand.nome()).ifPresent(restaurante -> {
            throw new RestauranteDuplicadoException(restaurante.getNome());
        });


        final Restaurante restaurante = Restaurante.create(restauranteCommand.nome(),
                restauranteCommand.tipoCozinha(),
                restauranteCommand.horarioFuncionamentoDe(),
                restauranteCommand.horarioFuncionamentoAte(),
                null);

        return restauranteRepository.incluir(restaurante);
    }

}
