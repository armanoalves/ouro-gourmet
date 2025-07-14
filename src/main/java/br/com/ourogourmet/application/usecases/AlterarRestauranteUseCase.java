package br.com.ourogourmet.application.usecases;

import java.time.LocalTime;

public interface AlterarRestauranteUseCase {

    void execute(AlterarRestauranteCommand restauranteCommand);

    record AlterarRestauranteCommand(
            Long id,
            String nome,
           String tipoCozinha,
           LocalTime horarioFuncionamentoDe,
           LocalTime horarioFuncionamentoAte,
           String usuarioId){

    }
}
