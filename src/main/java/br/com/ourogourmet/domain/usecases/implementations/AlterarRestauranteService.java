package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.usecases.AlterarRestauranteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlterarRestauranteService implements AlterarRestauranteUseCase {

    @Override
    public void execute(AlterarRestauranteCommand restauranteCommand,RestauranteGateway restauranteRepository) {

        var optionalRestaurante = restauranteRepository.buscarPorId(restauranteCommand.id());

        optionalRestaurante.ifPresent(restaurante->{

            restaurante.setNome(restauranteCommand.nome());
            restaurante.setTipoCozinha(restauranteCommand.tipoCozinha());
            restaurante.setHorarioFuncionamento(restauranteCommand.horarioFuncionamentoDe(),restauranteCommand.horarioFuncionamentoAte());

            restauranteRepository.alterar(restaurante);

        });

    }
}
