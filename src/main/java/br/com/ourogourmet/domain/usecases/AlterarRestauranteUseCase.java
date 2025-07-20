package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.gateway.RestauranteGateway;

import java.time.LocalTime;

public interface AlterarRestauranteUseCase {

    void execute(AlterarRestauranteCommand restauranteCommand, RestauranteGateway restauranteRepositor);

    record AlterarRestauranteCommand(
            Long id,
            String nome,
            String tipoCozinha,
            LocalTime horarioFuncionamentoDe,
            LocalTime horarioFuncionamentoAte,
            String usuarioId){

    }
}
