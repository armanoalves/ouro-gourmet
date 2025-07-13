package br.com.ourogourmet.application.usecases;

import java.time.LocalTime;

public interface CriarRestauranteUseCase {
    Long execute(CriarRestauranteCommand restauranteCommand);

    record CriarRestauranteCommand(String nome, String tipoCozinha, LocalTime horarioFuncionamentoDe, LocalTime horarioFuncionamentoAte){

    }
}
