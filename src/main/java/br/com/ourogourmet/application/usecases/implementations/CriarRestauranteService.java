package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.RestauranteGateway;
import br.com.ourogourmet.application.usecases.CriarRestauranteUseCase;
import br.com.ourogourmet.core.entities.Restaurante;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CriarRestauranteService implements CriarRestauranteUseCase {

    private final RestauranteGateway restauranteRepository;

    @Override
    public Long execute(CriarRestauranteCommand restauranteCommand) {

        /*this.restauranteRepository.findByNome(restauranteCommand.nome()).ifPresent(restaurante -> {
            throw new RestauranteDuplicadoException(restaurante.getNome());
        });*/


        final Restaurante restaurante = Restaurante.create(restauranteCommand.nome(),
                restauranteCommand.tipoCozinha(),
                restauranteCommand.horarioFuncionamentoDe(),
                restauranteCommand.horarioFuncionamentoAte(),
                null);

        return this.restauranteRepository.incluir(restaurante);
    }

}
