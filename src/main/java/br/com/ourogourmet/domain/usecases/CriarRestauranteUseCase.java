package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.gateway.RestauranteGateway;

import java.time.LocalTime;

public interface CriarRestauranteUseCase {
    Long execute(CriarRestauranteCommand restauranteCommand, RestauranteGateway restauranteRepository);

    record CriarRestauranteCommand(String nome,
                                   String tipoCozinha,
                                   LocalTime horarioFuncionamentoDe,
                                   LocalTime horarioFuncionamentoAte){

    }
}
